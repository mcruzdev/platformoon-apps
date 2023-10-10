package com.github.platformoon.presentation.requests.github;

public record GithubEventRequest(
        String ref,
        String action,
        GithubHeadCommit head_commit,
        GithubRepository repository
) {
}

