package com.bsg6.chapter09.mongodb;

import com.bsg6.chapter09.common.BaseArtist;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import java.util.Objects;
import java.util.StringJoiner;

@Document
public class Artist implements BaseArtist<String> {
    @Id
    String id;
    @NonNull
    String name;

    public Artist() {
    }

    public Artist(@NonNull String name) {
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    @NonNull
    public String getName() {
        return name;
    }

    @Override
    public void setName(@NonNull String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Artist.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("name='" + name + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist artist)) return false;
        return Objects.equals(
                getId(),
                artist.getId()
        ) && Objects.equals(
                getName(),
                artist.getName()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
