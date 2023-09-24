package com.github.platformoon.presentation.requests;

public record CreateApplicationRequest(
    String applicationName, String kind, String language, String description) {}
