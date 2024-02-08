package com.bsg6.chapter10;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value={"/", "/home"})
    public String home() {
        return "home";
    }
}
