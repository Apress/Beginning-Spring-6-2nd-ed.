package com.bsg6.chapter09.jpa;

import com.bsg6.chapter09.common.BaseSongRepository;

public interface SongRepository
    extends BaseSongRepository<Artist, Song, Integer> {
}
