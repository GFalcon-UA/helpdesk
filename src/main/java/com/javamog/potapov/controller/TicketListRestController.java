package com.javamog.potapov.controller;


import com.javamog.potapov.model.Ticket;
import com.javamog.potapov.model.User;
import com.javamog.potapov.service.UserService;
import com.javamog.potapov.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ticket-list")
public class TicketListRestController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Ticket>> ShowHelloPage() {
        User user = userService.getUser(UserUtils.getLoggedInUserEmail());
        List<Ticket> tickets = userService.getUserTickets(user);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

}
