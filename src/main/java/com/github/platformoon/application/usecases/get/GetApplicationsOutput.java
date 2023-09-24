package com.github.platformoon.application.usecases.get;

import com.github.platformoon.domain.application.Application;

import java.util.List;

public record GetApplicationsOutput(List<Application> content) {}
