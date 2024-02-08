package com.bsg6.chapter10;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecureController {
    @GetMapping(value={"/", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/admin_dashboard")
    public String admin_dashboard() {
        return "admin_dashboard";
    }

}
