package com.github.platformoon.application.usecases.application.create;

public record CreateApplicationCommand(
    String applicationName, String language, String kind, String description) {}
