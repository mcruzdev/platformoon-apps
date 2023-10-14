package com.github.platformoon.domain.ci;

public class Pipeline {

    private final String ref;
    private final String cloneUrl;
    private final String sha;
    private final String action;
    private final String applicationName;
    private final String repo;

    private Pipeline(String sha, String cloneUrl, String ref, String action, String applicationName, String repo) {
        this.sha = sha;
        this.cloneUrl = cloneUrl;
        this.ref = ref;
        this.action = action;
        this.applicationName = applicationName;
        this.repo = repo;
    }

    public static Pipeline create(String sha, String cloneUrl, String ref, String action, String applicationName, String repo) {
        return new Pipeline(sha, cloneUrl, ref, action, applicationName, repo);
    }

    public String getRef() {
        return ref;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public String getSha() {
        return sha;
    }

    public String getAction() {
        return action;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getRepo() {
        return repo;
    }
}
