package com.github.platformoon.presentation.requests.github;

public final class GithubRepository {
    private String clone_url;


    public String getClone_url() {
        return clone_url;
    }

    public void setClone_url(String clone_url) {
        this.clone_url = clone_url;
    }

    @Override
    public String toString() {
        return "GithubRepository[" +
                "clone_url=" + clone_url + ']';
    }

}
