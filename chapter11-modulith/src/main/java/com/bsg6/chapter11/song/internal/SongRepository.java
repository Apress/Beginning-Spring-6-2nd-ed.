package com.bsg6.chapter11.song.internal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Integer> {
}
