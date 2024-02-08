package com.bsg6.chapter09.jpa;

import com.bsg6.chapter09.common.BaseSong;
import org.springframework.lang.NonNull;

import jakarta.persistence.*;

import java.util.StringJoiner;

@Entity
@Table(indexes = {
        @Index(
                name = "artist_song",
                columnList = "artist_id,name",
                unique = true
        )
})
public class Song implements BaseSong<Artist, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @ManyToOne
    @NonNull
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
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Artist getArtist() {
        return artist;
    }

    @Override
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
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

    @Override
    public String toString() {
        return new StringJoiner(", ", Song.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("artist=" + artist)
                .add("votes=" + votes)
                .toString();
    }
}
