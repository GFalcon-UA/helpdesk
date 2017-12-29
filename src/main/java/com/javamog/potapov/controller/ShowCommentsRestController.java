package com.javamog.potapov.controller;

import com.javamog.potapov.model.Comment;
import com.javamog.potapov.model.Ticket;
import com.javamog.potapov.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/showComments/{id}")
public class ShowCommentsRestController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<Comment>> showOverviewPage(@PathVariable("id") Long id) {
        Ticket ticket = ticketService.getTicketById(id);
        List<Comment> comments = ticket.getTicketComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

}
