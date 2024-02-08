package com.bsg6.chapter10;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NonSecureController {

    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false) String error) {
        model.addAttribute("hasError", error != null);
        return "login";
    }
}