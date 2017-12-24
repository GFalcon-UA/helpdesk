package com.javamog.potapov.controller;

import com.javamog.potapov.model.History;
import com.javamog.potapov.model.Ticket;
import com.javamog.potapov.model.User;
import com.javamog.potapov.service.TicketService;
import com.javamog.potapov.service.UserService;
import com.javamog.potapov.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/showHistory/{id}")
public class ShowHistoryRestController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<History>> ShowOverviewPage(@PathVariable("id") int id) {
        Ticket ticket = ticketService.getTicketById(id);
        List<History> histories = ticket.getTicketHistory();
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }

}
