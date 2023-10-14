package com.github.platformoon.application.usecases.ci.data;

public record CreatePipelineOnPullRequestCommand(
        String event,
        String ref,
        String sha,
        String action,
        String cloneUrl,
        String fullName,
        String name) {
}