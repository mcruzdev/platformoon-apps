package com.github.platformoon.domain.application;

import com.github.platformoon.domain.application.version.ApplicationVersion;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApplicationVersionRepository implements PanacheRepository<ApplicationVersion> {
}
