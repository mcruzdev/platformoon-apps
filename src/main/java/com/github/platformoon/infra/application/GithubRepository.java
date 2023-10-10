package com.github.platformoon.infra.application;

import com.github.platformoon.domain.application.GitRepository;
import org.kohsuke.github.GitHub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GithubRepository implements GitRepository {

    private final GitHub github;
    private static final Logger LOGGER = LoggerFactory.getLogger(GithubRepository.class);
    private final String organization;

    public GithubRepository(final GitHub github, String organization) {
        this.github = github;
        this.organization = organization;
    }

    @Override
    public Optional<String> create(final String name) {

        try {
            var builder = this.github.getOrganization(organization);
            var repository = builder.createRepository(name).private_(true);
            return Optional.of(repository.create().getFullName());
        } catch (IOException e) {
            LOGGER.error("error creating a repository using Github API", e);
            return Optional.empty();
        }
    }

    @Override
    public List<String> findVersions(String applicationId) {

        try {
            return this.github.getOrganization(organization)
                    .getRepository(applicationId)
                    .listTags()
                    .toList()
                    .stream()
                    .map(tag -> tag.getName())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("error listing repository tags", e);
            throw new RuntimeException(e);
        }
    }
}
