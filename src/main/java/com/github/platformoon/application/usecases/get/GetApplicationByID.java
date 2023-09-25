package com.github.platformoon.application.usecases.get;

import com.github.platformoon.domain.application.Application;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class GetApplicationByID {

  private final EntityManager entityManager;

  @Inject
  public GetApplicationByID(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public GetApplicationByIDOutput execute(final String id) {
    Application application = this.entityManager.find(Application.class, id);

    return new GetApplicationByIDOutput(
        application.getId(),
        application.getName(),
        application.getLanguage().name(),
        application.getKind().name(),
        application.getDescription());
  }
}
