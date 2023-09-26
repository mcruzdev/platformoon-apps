package com.github.platformoon.infra.k8s;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.quarkus.kubernetes.client.KubernetesClientObjectMapper;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;

@Dependent
public class K8SConfiguration {

  @Produces
  public KubernetesClient kubernetesClient() {
    return new DefaultKubernetesClient();
  }

  @KubernetesClientObjectMapper
  @Singleton
  @Produces
  public ObjectMapper kubernetesClientObjectMapper() {
    return new ObjectMapper();
  }
}
