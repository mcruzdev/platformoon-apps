package com.github.platformoon.presentation.requests.github;

public class GithubEventRequest {
    private String ref;
    private String action;
    private GithubPullRequest pull_request;
    private GithubRepository repository;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public GithubPullRequest getPull_request() {
        return pull_request;
    }

    public void setPull_request(GithubPullRequest pull_request) {
        this.pull_request = pull_request;
    }

    public GithubRepository getRepository() {
        return repository;
    }

    public void setRepository(GithubRepository repository) {
        this.repository = repository;
    }

    @Override
    public String toString() {
        return "GithubEventRequest{" +
                "ref='" + ref + '\'' +
                ", action='" + action + '\'' +
                ", pull_request=" + pull_request +
                ", repository=" + repository +
                '}';
    }
}

