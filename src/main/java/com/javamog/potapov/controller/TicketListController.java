package com.javamog.potapov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticketList")
public class TicketListController {

    @GetMapping
    public String showHelloPage(Model model) {
        return "ticket_list";
    }
}
