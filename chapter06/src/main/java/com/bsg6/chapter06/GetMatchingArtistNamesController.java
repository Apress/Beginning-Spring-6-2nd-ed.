package com.bsg6.chapter06;

import com.bsg6.chapter03.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GetMatchingArtistNamesController {

    MusicService service;

    GetMatchingArtistNamesController(MusicService service) {
        this.service=service;
    }

    @GetMapping(value = "/artists",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<String>> getMatchingArtistNames(
            @RequestParam(name="prefix") String prefix
    ) {
        var artistNames = service.getMatchingArtistNames(prefix);

        return new ResponseEntity<>(artistNames, HttpStatus.OK);
    }
}
