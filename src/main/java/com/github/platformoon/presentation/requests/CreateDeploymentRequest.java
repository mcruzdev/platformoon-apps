package com.github.platformoon.presentation.requests;

public record CreateDeploymentRequest(String applicationName, String environment, String applicationVersion) {}
