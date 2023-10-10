package com.github.platformoon.domain.application.version;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "versions")
public class ApplicationVersion {

    @Id
    private String id;
    private String applicationName;
    private String commitAuthorName;
    private String commitAuthorUsername;
    private String branchName;
    private String commitId;
    private String commitUrl;

    @Enumerated(EnumType.STRING)
    private ApplicationVersionStatus status;

    protected ApplicationVersion() {
    }

    protected ApplicationVersion(String applicationName, String commitAuthorName, String commitAuthorUsername, String branchName, String commitId, String commitUrl) {
        this.id = UUID.randomUUID().toString();
        this.applicationName = applicationName;
        this.commitAuthorName = commitAuthorName;
        this.commitAuthorUsername = commitAuthorUsername;
        this.branchName = branchName;
        this.commitId = commitId;
        this.commitUrl = commitUrl;
        this.status = ApplicationVersionStatus.SAVED;
    }

    public static ApplicationVersion create(String applicationName, String commitAuthorName, String commitAuthorUsername, String branchName, String commitId, String commitUrl) {
        return new ApplicationVersion(
                applicationName,
                commitAuthorName,
                commitAuthorUsername,
                branchName,
                commitId,
                commitUrl
        );
    }

    public String getId() {
        return id;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getCommitAuthorName() {
        return commitAuthorName;
    }

    public String getCommitAuthorUsername() {
        return commitAuthorUsername;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getCommitId() {
        return commitId;
    }

    public String getCommitUrl() {
        return commitUrl;
    }
}
