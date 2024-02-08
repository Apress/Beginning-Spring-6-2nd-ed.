package com.bsg6.chapter08;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value= HttpStatus.BAD_REQUEST,
        reason="No artist name submitted")
public class NoArtistNameSubmittedException
        extends ArtistNotFoundException {
}
