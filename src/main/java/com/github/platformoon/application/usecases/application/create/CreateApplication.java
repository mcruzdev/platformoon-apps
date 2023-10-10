package com.github.platformoon.application.usecases.application.create;

import com.github.platformoon.domain.application.Application;
import com.github.platformoon.domain.application.GitRepository;
import com.github.platformoon.domain.application.Kind;
import com.github.platformoon.domain.application.Language;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.io.IOException;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class CreateApplication {

  private final Logger LOGGER = LoggerFactory.getLogger(CreateApplication.class);
  private final GitRepository gitRepository;
  @Inject EntityManager entityManager;

  @Inject
  public CreateApplication(GitRepository gitRepository) {
    this.gitRepository = gitRepository;
  }

  @Transactional
  public CreateApplicationOutput execute(CreateApplicationCommand command) {

    try {

      Application newApplication =
          Application.create(
              command.applicationName(),
              command.description(),
              Language.valueOf(command.language()),
              Kind.valueOf(command.kind()));

      LOGGER.info("creating new application: {}", newApplication);

      entityManager.persist(newApplication);

      this.gitRepository
          .create(command.applicationName())
          .orElseThrow(() -> new RuntimeException("error creating repository, try again"));

      return new CreateApplicationOutput(newApplication.getId());

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
