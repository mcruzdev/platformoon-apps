package com.github.platformoon.application.usecases.application.create;

import com.github.platformoon.domain.application.ApplicationVersionRepository;
import com.github.platformoon.domain.application.version.ApplicationVersion;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Optional;

@ApplicationScoped
public class CreateApplicationVersion {

    enum EventType {
        PUSH("push");

        private final String eventName;

        EventType(String eventName) {
            this.eventName = eventName;
        }

        static EventType from(String name) {
            return EventType.valueOf(name);
        }
    }

    private final ApplicationVersionRepository applicationVersionRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateApplicationVersion.class);

    @Inject
    CreateApplicationVersion(ApplicationVersionRepository applicationVersionRepository) {
        this.applicationVersionRepository = applicationVersionRepository;
    }

    @Transactional
    public Optional<String> execute(CreateApplicationVersionCommand command) {

        if (!Objects.equals(EventType.PUSH.eventName, command.event())) {
            LOGGER.info("[flow:create-application-version] rejecting event name {}", command.event());
            return Optional.empty();
        }

        ApplicationVersion applicationVersion = ApplicationVersion.create(
                command.repoName(),
                command.commitAuthorName(),
                command.commitAuthorUsername(),
                command.ref(),
                command.commitId(),
                command.commitUrl()
        );

        this.applicationVersionRepository.persist(applicationVersion);

        return Optional.of(applicationVersion.getId());
    }
}
