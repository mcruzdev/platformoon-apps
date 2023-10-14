package com.github.platformoon.presentation.resources;

public class GithubRepo {

    String full_name;
    String name;

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GithubRepo{" +
                "full_name='" + full_name + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
