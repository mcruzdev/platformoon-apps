package com.github.platformoon.application.usecases.create;

public record CreateApplicationCommand(
    String applicationName, String language, String kind, String description) {}
