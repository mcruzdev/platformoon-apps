package com.github.platformoon.application.usecases.get;

import com.github.platformoon.domain.application.Application;
import com.github.platformoon.domain.application.ApplicationRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class GetApplications {


    private final ApplicationRepository applicationRepository;

    public GetApplications(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public GetApplicationsOutput execute() {
        List<Application> applications =
                this.applicationRepository.listAll();
        return new GetApplicationsOutput(applications);
    }
}
