package com.github.platformoon.domain.application.deployment;

public interface DeploymentManager {

  void deploy(String applicationName, String environment, String version);
}
