package com.bsg6.chapter11.artist.internal;

import com.bsg6.chapter11.artist.ArtistDTO;
import com.bsg6.chapter11.artist.ArtistService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtistController {
    private final ArtistService service;

    public ArtistController(ArtistService service) {
        this.service = service;
    }

    @GetMapping(value="/artists/{artistId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ArtistDTO getArtist(@PathVariable Integer artistId) {
        return service.getArtist(artistId);
    }

}
