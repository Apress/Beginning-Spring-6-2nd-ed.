package com.bsg6.chapter09.mongodb;

import com.bsg6.chapter09.test.BaseArtistRepositoryTests;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
public class ArtistRepositoryTests
    extends BaseArtistRepositoryTests<Artist, String> {
    protected Artist createArtist(String name) {
        return new Artist(name);
    }
}
