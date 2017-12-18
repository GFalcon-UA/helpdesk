package com.javamog.potapov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticketOverview")
public class TicketOverviewController {

    @GetMapping
    public String showTicketOverviewPage(Model model) {
        return "ticket_overview";
    }
}
