package com.javamog.potapov.controller;

import com.javamog.potapov.model.Comment;
import com.javamog.potapov.model.History;
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
@RequestMapping("/showComments")
public class ShowCommentsRestController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Comment>> ShowOverviewPage() {
        User user = userService.getUser("user1_mogilev@yopmail.com");
        Ticket ticket = user.getOwnTickets().get(0);
        List<Comment> comments = ticket.getTicketComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

}
