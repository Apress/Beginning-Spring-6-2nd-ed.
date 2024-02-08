package com.bsg6.chapter06;

import com.bsg6.chapter03.MusicService;
import com.bsg6.chapter03.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GetArtistsExceptionController {

    MusicService service;

    GetArtistsExceptionController(MusicService service) {
        this.service = service;
    }

    @GetMapping("/artists/{artist}")
    @ResponseBody
    public ResponseEntity<Artist> getSong(
            @PathVariable("artist") final String artist
    ) {
        throw new ArtistNotFoundException("Artist with name " + artist + " not found");
    }

    @ExceptionHandler(ArtistNotFoundException.class)
    public ModelAndView handleCustomException(ArtistNotFoundException ex) {

        var model = new ModelAndView("error");
        model.addObject("message", ex.getMessage());
        model.addObject("statusCode", 404);
        return model;

    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllExceptions(Exception ex) {

        var model = new ModelAndView("error");
        model.addObject("message", ex.getMessage());
        model.addObject("statusCode", 500);
        return model;
    }

}
