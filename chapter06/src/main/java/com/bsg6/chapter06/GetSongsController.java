package com.bsg6.chapter06;

import com.bsg6.chapter03.MusicService;
import com.bsg6.chapter03.model.Song;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class GetSongsController {
    MusicService service;

    GetSongsController(MusicService service) {
        this.service = service;
    }

    @GetMapping("/artists/{artist}/songs/{name}")
    public ResponseEntity<Song> getSong(
            @PathVariable("artist") final String artist,
            @PathVariable("name") final String name
    ) {
        var artistDecoded = URLDecoder.decode(artist, StandardCharsets.UTF_8);
        var nameDecoded = URLDecoder.decode(name, StandardCharsets.UTF_8);
        var song = service.getSong(artistDecoded, nameDecoded);

        return new ResponseEntity<>(song, HttpStatus.OK);
    }

    @GetMapping("/songs")
        public ResponseEntity<List<Song>> getSongsByArtist(
            @RequestParam(name = "artist") String artist
    ) {
        var data = service.getSongsForArtist(artist);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
