package com.github.platformoon.domain.application.deployments;

public interface DeploymentManager {

  void deploy(String applicationName, String environment, String version);
}
