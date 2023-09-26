package com.github.platformoon.domain.application.deployments;

public record Version(String registryAccount, String application, String version) {
  public String fullImageName() {
    if (this.version == null || this.version.isBlank()) {
      return String.format("%s/%s:latest", registryAccount, application);
    }
    return String.format("%s/%s:%s", registryAccount, application, version);
  }
}
