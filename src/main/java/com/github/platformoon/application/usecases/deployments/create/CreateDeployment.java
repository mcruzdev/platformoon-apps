package com.github.platformoon.application.usecases.deployments.create;

import com.github.platformoon.domain.application.Application;
import com.github.platformoon.domain.application.deployment.DeploymentManager;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class CreateDeployment {

  private final EntityManager entityManager;
  private final DeploymentManager deploymentManager;

  @Inject
  public CreateDeployment(
      final EntityManager entityManager, final DeploymentManager deploymentManager) {
    this.entityManager = entityManager;
    this.deploymentManager = deploymentManager;
  }

  public void execute(final CreateDeploymentInput input) {
    Application app =
        this.entityManager
            .createQuery("select a from Application a where a.name = :name", Application.class)
            .setParameter("name", input.applicationName())
            .getSingleResult();

    this.deploymentManager.deploy(app.getName(), input.environment(), input.version());
  }
}
