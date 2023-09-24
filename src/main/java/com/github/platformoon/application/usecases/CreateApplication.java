package com.github.platformoon.application.usecases;

import com.github.platformoon.domain.application.Application;
import com.github.platformoon.domain.application.GitRepository;
import com.github.platformoon.domain.application.Kind;
import com.github.platformoon.domain.application.Language;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class CreateApplication {

  private final Logger LOGGER = LoggerFactory.getLogger(CreateApplication.class);
  private final GitRepository gitRepository;

  @Inject
  public CreateApplication(GitRepository gitRepository) {
    this.gitRepository = gitRepository;
  }

  public CreateApplicationOutput execute(CreateApplicationCommand command) {

    try {
      String fullName =
          this.gitRepository
              .create(command.applicationName())
              .orElseThrow(() -> new RuntimeException("error creating repository, try again"));

      Application newApplication =
          Application.create(
              fullName,
              command.description(),
              Language.valueOf(command.language()),
              Kind.valueOf(command.kind()));

      LOGGER.info("creating new application: {}", newApplication);

      return new CreateApplicationOutput(newApplication.getId());

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
