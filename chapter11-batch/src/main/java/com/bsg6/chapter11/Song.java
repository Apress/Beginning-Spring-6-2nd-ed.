package com.bsg6.chapter11;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @ManyToOne
    @NonNull
    Artist artist;

    @Transient
    transient Integer artistId;

    @NonNull
    String name;
    int votes;

    public Song() {}

    public Song(String name) {
        this.name = name;
    }

    public Song(String name, Artist artist) {
        this.name = name;
        this.artist = artist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NonNull
    public Artist getArtist() {
        return artist;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public void setArtist(@NonNull Artist artist) {
        this.artist = artist;
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
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", artist=" + artist +
                ", name='" + name + '\'' +
                ", votes=" + votes +
                '}';
    }
}