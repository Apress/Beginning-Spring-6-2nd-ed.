package com.bsg6.chapter09.jpa;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bsg6.chapter09.test.BaseSongRepositoryTests;

@DataJpaTest
public class SongRepositoryTests
    extends BaseSongRepositoryTests<Artist, Song, Integer> {
    @Override
    protected Artist createArtist(String name) {
        return new Artist(name);
    }

    @Override
    protected Song createSong(Artist artist, String name) {
        return new Song(artist, name);
    }
}
