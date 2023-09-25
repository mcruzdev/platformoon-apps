package com.github.platformoon.application.usecases.get;

public record GetApplicationByIDOutput(
    String id, String name, String language, String kind, String description) {}
