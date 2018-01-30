package com.javamog.potapov.controller;

import com.javamog.potapov.model.Ticket;
import com.javamog.potapov.model.User;
import com.javamog.potapov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
@RequestMapping("/ticket-list")
public class TicketListRestController {

    @Autowired
    private UserService userService;

    private static String loggedInUser = "user1_mogilev@yopmail.com";

    @GetMapping
    public ResponseEntity<List<Ticket>> ShowHelloPage() {
        User user = userService.getUser(loggedInUser);
        //List<Ticket> tickets = user.getOwnTickets();
        List<Ticket> tickets = userService.getUserTickets(user);
        //return new ResponseEntity<>(user, HttpStatus.OK);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }


    @PostMapping
    /*public String submitLogin(Model model, @RequestParam("email") String email,
                              @RequestParam("password") String password) {

        User user = userService.getUser(email);
        model.addAttribute("userName", user.getFirstName());
        model.addAttribute("tickets", user.getOwnTickets());
        return "hello";
    }*/
    public ResponseEntity<Void> submitLogin(@RequestBody String email) {

        loggedInUser = email;
        //User user = userService.getUser("user1_mogilev@yopmail.com");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
