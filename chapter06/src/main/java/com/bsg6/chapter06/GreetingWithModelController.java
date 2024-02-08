package com.bsg6.chapter06;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GreetingWithModelController {
    @GetMapping(name = "greeting", path = "/greeting/{name}")
    public String greeting(@PathVariable(name="name") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
