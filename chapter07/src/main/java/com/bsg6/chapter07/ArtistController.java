package com.bsg6.chapter07;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
public class ArtistController {
    private ArtistRepository service;

    public ArtistController(ArtistRepository service) {
        this.service = service;
    }

    @GetMapping("/artist/{id}")
    Artist findArtistById(@PathVariable int id) throws SQLException {
        return service.findArtistById(id);
    }

    @GetMapping({"/artist/search/{name}", "/artist/search/"})
    Artist findArtistByName(
            @PathVariable(required = false) String name
    ) throws SQLException {
        if (name != null) {
            return service.findArtistByName(name);
        } else {
            throw new IllegalArgumentException("No artist name submitted");
        }
    }

    @PostMapping("/artist")
    Artist saveArtist(@RequestBody Artist artist) throws SQLException {
        return service.saveArtist(artist.getName());
    }

    @GetMapping({"/artist/match/{name}", "/artist/match/"})
    List<Artist> findArtistByMatchingName(
            @PathVariable(required = false)
            String name
    ) throws SQLException {
        return service.findAllArtistsByName(name != null ? name : "");
    }

    @ExceptionHandler(ArtistNotFoundException.class)
    ProblemDetail handleArtistNotFound(ArtistNotFoundException e) {
        ProblemDetail problemDetails = ProblemDetail
                .forStatusAndDetail
                        (HttpStatus.NOT_FOUND, e.getLocalizedMessage());

        problemDetails.setTitle("Artist Not Found");
        return problemDetails;
    }


}
