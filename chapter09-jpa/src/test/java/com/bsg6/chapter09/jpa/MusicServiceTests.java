package com.bsg6.chapter09.jpa;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bsg6.chapter09.test.BaseMusicServiceTests;

@DataJpaTest
public class MusicServiceTests
    extends BaseMusicServiceTests<Artist, Song, Integer> {
    @Override
    protected Integer getNonexistentId() {
        return 1928491;
    }
}
