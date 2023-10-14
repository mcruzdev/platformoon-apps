package com.github.platformoon.presentation.requests.github;

public class GithubPullRequest {

    private GithubHead head;

    public GithubHead getHead() {
        return head;
    }

    public void setHead(GithubHead head) {
        this.head = head;
    }

    @Override
    public String toString() {
        return "GithubPullRequest{" +
                "head=" + head +
                '}';
    }
}
