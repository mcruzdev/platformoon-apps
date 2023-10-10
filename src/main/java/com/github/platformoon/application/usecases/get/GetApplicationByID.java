package com.github.platformoon.application.usecases.get;

import com.github.platformoon.domain.application.Application;
import com.github.platformoon.domain.application.ApplicationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class GetApplicationByID {

    private final ApplicationRepository applicationRepository;

    @Inject
    public GetApplicationByID(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public GetApplicationByIDOutput execute(final String id) {
        Application application = this.applicationRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        return new GetApplicationByIDOutput(
                application.getId(),
                application.getName(),
                application.getLanguage().name(),
                application.getKind().name(),
                application.getDescription());
    }
}
