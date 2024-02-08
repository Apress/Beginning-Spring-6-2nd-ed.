package com.bsg6.chapter09.mongodb;

import com.bsg6.chapter09.common.BaseSong;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

@Document
@CompoundIndexes(
        @CompoundIndex(unique = true, def = "{'artist':1, 'name':1}")
)
public class Song implements BaseSong<Artist, String> {
    @Id
    String id;
    @NonNull
    @DBRef
    Artist artist;
    @NonNull
    String name;
    int votes;

    public Song() {
    }

    public Song(@NonNull Artist artist, @NonNull String name) {
        this.artist = artist;
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
    public Artist getArtist() {
        return artist;
    }

    @Override
    public void setArtist(@NonNull Artist artist) {
        this.artist = artist;
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
    public int getVotes() {
        return votes;
    }

    @Override
    public void setVotes(int votes) {
        this.votes = votes;
    }
}
