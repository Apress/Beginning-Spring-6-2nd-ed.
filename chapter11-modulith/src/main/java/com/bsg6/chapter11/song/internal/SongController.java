package com.bsg6.chapter11.song.internal;

import com.bsg6.chapter11.song.SongDTO;
import com.bsg6.chapter11.song.SongService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SongController {

    private final SongService service;

    public SongController(SongService service) {
        this.service = service;
    }

    @GetMapping(value="/songs/{songId}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    SongDTO getSong(@PathVariable Integer songId) {
        return service.getSong(songId);
    }
}
