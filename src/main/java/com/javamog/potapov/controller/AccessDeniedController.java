package com.javamog.potapov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Access_Denied")
public class AccessDeniedController {
    @GetMapping
    public String showHomePage(Model model) {
        return "access_denied";
    }
}
