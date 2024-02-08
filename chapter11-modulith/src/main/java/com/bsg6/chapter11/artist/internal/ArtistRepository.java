package com.bsg6.chapter11.artist.internal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
}