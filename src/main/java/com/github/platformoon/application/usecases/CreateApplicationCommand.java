package com.github.platformoon.application.usecases;

public record CreateApplicationCommand(
    String applicationName, String language, String kind, String description) {}
