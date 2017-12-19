package com.javamog.potapov.controller;

import com.javamog.potapov.model.Attachment;
import com.javamog.potapov.model.Ticket;
import com.javamog.potapov.model.User;
import com.javamog.potapov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ticket-overview")
public class TicketOverviewRestController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Ticket> ShowOverviewPage() {
        User user = userService.getUser("user1_mogilev@yopmail.com");
        Ticket ticket = user.getOwnTickets().get(0);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }
}
