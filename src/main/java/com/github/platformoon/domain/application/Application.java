package com.github.platformoon.domain.application;

import com.google.common.base.MoreObjects;

import java.util.UUID;

public class Application {

  String id;
  String name;
  Language language;
  Kind kind;
  String description;

  private Application(final String name, final String description, Language language, Kind kind) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.description = description;
    this.language = language;
    this.kind = kind;
  }

  public static Application create(
      final String name, final String description, Language language, Kind kind) {
    return new Application(name, description, language, kind);
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Language getLanguage() {
    return language;
  }

  public Kind getKind() {
    return kind;
  }

  public String getDescription() {
    return description;
  }

  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", this.id)
        .add("name", this.name)
        .add("description", this.description)
        .add("kind", this.kind)
        .add("language", this.language)
        .toString();
  }
}
