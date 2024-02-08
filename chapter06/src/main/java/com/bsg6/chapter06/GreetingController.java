package com.bsg6.chapter06;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

    @GetMapping(
            path = "/greeting",
            produces = {MediaType.TEXT_PLAIN_VALUE}
    )

    public ResponseEntity<String> greeting() {
        return new ResponseEntity<>(
                "Hello, World!",
                HttpStatus.OK
        );
    }

}
