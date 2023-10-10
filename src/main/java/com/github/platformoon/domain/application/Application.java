package com.github.platformoon.domain.application;

import com.google.common.base.MoreObjects;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@NamedQuery(name = "Applications.findAll", query = "SELECT app FROM Application app ORDER BY app.name")
@Table(name = "applications")
public class Application {

    @Id
    String id;
    String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "language", length = 100)
    Language language;

    @Enumerated(EnumType.STRING)
    Kind kind;

    String description;

    protected Application() {
    }

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
