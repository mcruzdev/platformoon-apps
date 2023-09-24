package com.github.platformoon.infra.application;

import com.github.platformoon.domain.application.GitRepository;
import java.io.IOException;
import java.util.Optional;
import org.kohsuke.github.GitHub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
      var repository = builder.createRepository(name);
      return Optional.of(repository.create().getFullName());
    } catch (IOException e) {
      LOGGER.error("error creating a repository using Github API", e);
      return Optional.empty();
    }
  }
}
