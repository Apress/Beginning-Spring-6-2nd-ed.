package com.bsg6.chapter11.song;

import com.bsg6.chapter11.artist.ArtistDTO;

public record SongDTO(Integer id, String name, ArtistDTO artistDTO) {
}
