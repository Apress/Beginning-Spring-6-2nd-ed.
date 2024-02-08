package com.bsg6.chapter10;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

// tag::csrfadvice[]
@ControllerAdvice
public class CsrfTokenControllerAdvice {

    @ModelAttribute("csrf")
    public CsrfToken csrfToken(CsrfToken token) {
        return token;
    }
}
// end::csrfadvice[]