package com.github.platformoon.presentation;

public record CreateApplicationRequest(
    String applicationName, String kind, String language, String description) {}
