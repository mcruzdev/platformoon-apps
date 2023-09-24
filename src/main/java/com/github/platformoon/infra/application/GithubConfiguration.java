package com.github.platformoon.infra.application;

import com.github.platformoon.domain.application.GitRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import java.io.IOException;
import java.util.Objects;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

@Dependent
public class GithubConfiguration {

  @ConfigProperty(name = "PLATFORMOON_GH_TOKEN")
  String token;

  @ConfigProperty(name = "PLATFORMOON_GH_ORG")
  String user;

  @Produces
  public GitRepository gitRepository() throws IOException {
    if (Objects.isNull(token) || token.isBlank()) {
      throw new IllegalArgumentException("PLATFORMOON_GH_TOKEN is not set");
    }
    GitHub gitHub = new GitHubBuilder().withJwtToken(token).build();
    return new GithubRepository(gitHub, user);
  }
}
