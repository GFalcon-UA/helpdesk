package com.javamog.potapov.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/auth", produces = "application/json")
public class AuthController {

    @RequestMapping("/getCurrentUser")
    public Principal user(Principal user) {
        return user;
    }

}
