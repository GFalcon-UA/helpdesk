package com.javamog.potapov.controller;

import com.javamog.potapov.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket-overview/{id}")
public class TicketOverviewRestController {

    @Autowired
    private TicketService ticketService;


}
