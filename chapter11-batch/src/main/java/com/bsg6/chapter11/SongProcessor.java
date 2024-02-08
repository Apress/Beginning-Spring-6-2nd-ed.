package com.bsg6.chapter11;

import org.springframework.batch.item.ItemProcessor;

public class SongProcessor implements ItemProcessor<Song, Song> {
    @Override
    public Song process(final Song song) {
        if (song.getArtistId() != null) {

            Artist artist = new Artist();
            artist.setId(song.getArtistId());
            song.setArtist(artist);
        }
        return song;
    }
}