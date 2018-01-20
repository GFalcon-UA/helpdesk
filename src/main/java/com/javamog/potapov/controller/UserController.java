package com.javamog.potapov.controller;

import com.javamog.potapov.model.ticket.Ticket;
import com.javamog.potapov.service.UserService;
import com.javamog.potapov.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/role")
    public ResponseEntity<GrantedAuthority> getRole() {
        GrantedAuthority grantedAuthority = UserUtils.getCurrentUser().getAuthorities().stream().findFirst().get();
        return new ResponseEntity<>(grantedAuthority, HttpStatus.OK);
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getTickets() {
        return new ResponseEntity<>(userService.getUser().getOwnTickets(), HttpStatus.OK);
    }
}
