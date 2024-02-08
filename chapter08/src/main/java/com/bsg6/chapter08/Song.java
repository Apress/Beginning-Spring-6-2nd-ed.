package com.bsg6.chapter08;

import org.springframework.lang.NonNull;

import java.util.Objects;
import java.util.StringJoiner;

public class Song {
    Integer id;
    @NonNull
    Integer artistId;
    @NonNull
    String name;
    int votes;

    public Song() {
    }

    public Song(Integer id, @NonNull Integer artistId, @NonNull String name, int votes) {
        this.id = id;
        this.artistId = artistId;
        this.name = name;
        this.votes = votes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NonNull
    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(@NonNull Integer artistId) {
        this.artistId = artistId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song song)) return false;
        return getVotes() == song.getVotes()
                && Objects.equals(getId(), song.getId())
                && Objects.equals(getArtistId(), song.getArtistId())
                && Objects.equals(getName(), song.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getArtistId(), getName(), getVotes());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Song.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("votes=" + votes)
                .toString();
    }
}
