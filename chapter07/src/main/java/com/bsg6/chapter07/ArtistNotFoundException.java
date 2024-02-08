package com.bsg6.chapter07;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArtistNotFoundException extends RuntimeException {
	public ArtistNotFoundException(String message) {
        super(message);
    }

    public ArtistNotFoundException(Exception e) {
        super(e);
    }
}
