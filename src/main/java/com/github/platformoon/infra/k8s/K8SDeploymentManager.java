package com.github.platformoon.infra.k8s;

import com.github.platformoon.domain.application.deployment.DeploymentManager;
import com.github.platformoon.domain.application.deployments.Version;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.DeploymentBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Optional;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class K8SDeploymentManager implements DeploymentManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(K8SDeploymentManager.class);
  private final KubernetesClient kubernetesClient;

  @ConfigProperty(name = "REGISTRY_ACCOUNT")
  String registryAccount;

  @Inject
  public K8SDeploymentManager(final KubernetesClient kubernetesClient) {
    this.kubernetesClient = kubernetesClient;
  }

  @Override
  public void deploy(String applicationName, String environment, String version) {

    var v = new Version(registryAccount, applicationName, version);
    String deploymentName = String.format("%s-%s", applicationName, environment);

    LOGGER.info("[flow:create-deployment] creating new deployment: {}", deploymentName);

    deleteIfExists(deploymentName);

    var deployment =
        new DeploymentBuilder()
            .withNewMetadata()
            .withName(deploymentName)
            .withNamespace("default")
            .endMetadata()
            .withNewSpec()
            .withReplicas(2)
            .withNewTemplate()
            .withNewMetadata()
            .addToLabels("app", applicationName)
            .addToLabels("version", version)
            .addToLabels("environment", environment)
            .endMetadata()
            .withNewSpec()
            .addNewContainer()
            .withName(applicationName)
            .withImage(v.fullImageName())
            .addNewPort()
            .withContainerPort(8080)
            .endPort()
            .endContainer()
            .endSpec()
            .endTemplate()
            .withNewSelector()
            .addToMatchLabels("app", applicationName)
            .endSelector()
            .endSpec()
            .build();

    kubernetesClient.apps().deployments().resource(deployment).create();
  }

  private void deleteIfExists(String deploymentName) {
    Optional<Deployment> possibleDeployment =
        this.kubernetesClient.apps().deployments().list().getItems().stream()
            .filter(item -> item.getMetadata().getName().equals(deploymentName))
            .findFirst();
    possibleDeployment.ifPresent(
        d -> this.kubernetesClient.apps().deployments().resource(d).delete());
  }
}
