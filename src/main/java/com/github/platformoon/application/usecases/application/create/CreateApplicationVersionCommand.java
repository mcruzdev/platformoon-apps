package com.github.platformoon.application.usecases.application.create;

public record CreateApplicationVersionCommand(
        String event,
        String repoName,
        String commitId,
        String commitUrl,
        String commitAuthorName,
        String commitAuthorUsername,
        String ref
) {
}