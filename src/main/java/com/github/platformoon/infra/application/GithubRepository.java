package com.github.platformoon.infra.application;

import com.github.platformoon.domain.application.GitRepository;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GithubRepository implements GitRepository {

    private static final String DEFAULT_BRANCH = "main";
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
            var org = this.github.getOrganization(organization);
            var repository = org.createRepository(name); // .private_(true); Only PRO or public repository can enable branch protection feature
            var newRepo = repository.defaultBranch(DEFAULT_BRANCH).create();

            createPlatformoonYmlFile(name, newRepo);

            createBranchProtectionRule(newRepo);

            return Optional.ofNullable(newRepo.getFullName());
        } catch (IOException e) {
            LOGGER.error("error creating a repository using Github API", e);
            return Optional.empty();
        }
    }

    private static void createBranchProtectionRule(GHRepository newRepo) throws IOException {
        newRepo
                .getBranch(DEFAULT_BRANCH)
                .enableProtection()
                .requiredReviewers(1)
                .enable();
    }

    private static void createPlatformoonYmlFile(String name, GHRepository repoCreated) throws IOException {
        repoCreated.createContent()
                .branch(DEFAULT_BRANCH)
                .path(".platformoon.yml")
                .message("chore: add .platformoon.yml file")
                .content(String.format("app: %s", name))
                .commit();
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
