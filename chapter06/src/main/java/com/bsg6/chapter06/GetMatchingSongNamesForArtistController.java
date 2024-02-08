package com.bsg6.chapter06;

import com.bsg6.chapter03.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GetMatchingSongNamesForArtistController {

    MusicService service;
    GetMatchingSongNamesForArtistController(MusicService service) {
        this.service=service;
    }

    @GetMapping(value = "/songnames")
    @ResponseBody
    public ResponseEntity<List<String>> getMatchingSongNamesForArtist(
            @RequestParam(name="artist") String artist,
            @RequestParam(name="prefix") String prefix
    ) {
        var songNames = service.getMatchingSongNamesForArtist(artist, prefix);

        return new ResponseEntity<>(songNames, HttpStatus.OK);
    }
}
