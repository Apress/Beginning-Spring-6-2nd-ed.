package com.bsg6.chapter08;

import org.springframework.lang.NonNull;

import java.util.Objects;
import java.util.StringJoiner;

public class Artist {
    Integer id;
    @NonNull
    String name;

    public Artist() {}

    public Artist(@NonNull String name) {
        this.name=name;
    }

    public Artist(Integer id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Artist.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist artist)) return false;
        return Objects.equals(getId(), artist.getId()) && Objects.equals(getName(), artist.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
