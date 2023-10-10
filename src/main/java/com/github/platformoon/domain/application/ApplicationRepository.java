package com.github.platformoon.domain.application;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ApplicationRepository implements PanacheRepository<Application> {

    public Optional<Application> findById(String id) {
        return Optional.ofNullable(find("id", id).firstResult());
    }
}
