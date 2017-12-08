package com.javamog.potapov.controller;


import com.javamog.potapov.model.Ticket;
import com.javamog.potapov.model.User;
import com.javamog.potapov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String ShowHelloPage(Model model/*, @ModelAttribute("tickets") List<Ticket> tickets*/) {
        //model.addAttribute("tickets", tickets);
        return "hello";
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public String submitLogin(Model model, @RequestParam("email") String email,
                              @RequestParam("password") String password) {

        User user = userService.getUser(email);
        model.addAttribute("userName", user.getFirstName());
        model.addAttribute("tickets", user.getOwnTickets());
        return "hello";
    }

}
