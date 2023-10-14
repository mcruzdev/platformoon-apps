package com.github.platformoon.presentation.requests.github;

import com.github.platformoon.presentation.resources.GithubRepo;

public class GithubHead {
    private String sha;
    private String ref;
    private GithubRepo repo;

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public GithubRepo getRepo() {
        return repo;
    }

    public void setRepo(GithubRepo repo) {
        this.repo = repo;
    }

    @Override
    public String toString() {
        return "GithubHead{" +
                "sha='" + sha + '\'' +
                ", ref='" + ref + '\'' +
                ", repo=" + repo +
                '}';
    }
}
