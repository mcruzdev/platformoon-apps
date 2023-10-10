package com.github.platformoon.domain.application;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface GitRepository {
    Optional<String> create(String name) throws IOException;

    List<String> findVersions(String applicationId);
}
