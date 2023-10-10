package com.github.platformoon.application.usecases.get;

import com.github.platformoon.domain.application.Application;
import com.github.platformoon.domain.application.ApplicationRepository;
import com.github.platformoon.domain.application.GitRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class GetVersionsByApplicationID {

    private final GitRepository githubRepository;
    private final ApplicationRepository applicationRepository;

    @Inject
    public GetVersionsByApplicationID(GitRepository githubRepository, ApplicationRepository applicationRepository) {
        this.githubRepository = githubRepository;
        this.applicationRepository = applicationRepository;
    }

    public GetApplicationVersionsOutput execute(String applicationId) {
        Application application
                = this.applicationRepository.findById(applicationId)
                .orElseThrow(NotFoundException::new);

        List<String> applicationVersions = this.githubRepository.findVersions(application.getName());

        return new GetApplicationVersionsOutput(applicationVersions);
    }
}
