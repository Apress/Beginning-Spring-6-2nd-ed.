package com.bsg6.chapter11.song.internal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Song {

    @Id
    private Integer id;

    private Integer artistId;

    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }
}
