package com.github.platformoon.application.usecases.deployments.create;

public record CreateDeploymentInput(String applicationName, String environment, String version) {}
