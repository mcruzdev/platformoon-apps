package com.github.platformoon.application.usecases.get;

import com.github.platformoon.domain.application.Application;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class GetApplications {

  private final EntityManager entityManager;

  public GetApplications(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public GetApplicationsOutput execute() {
    List<Application> applications =
        this.entityManager
            .createNamedQuery("Applications.findAll", Application.class)
            .getResultList();
    return new GetApplicationsOutput(applications);
  }
}
