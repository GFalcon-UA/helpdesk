package com.javamog.potapov.controller;

import com.javamog.potapov.model.Attachment;
import com.javamog.potapov.model.Ticket;
import com.javamog.potapov.model.User;
import com.javamog.potapov.service.TicketService;
import com.javamog.potapov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ticketOverview")
public class TicketOverviewController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    /*@ModelAttribute("ticket")
    public Ticket ticket() {
        User user = userService.getUser("user1_mogilev@yopmail.com");
        Ticket ticket = user.getOwnTickets().get(0);
        return ticket;
        //return new Ticket();
    }*/

    @GetMapping
    public String showTicketOverviewPage(Model model) {
        User user = userService.getUser("user1_mogilev@yopmail.com");
        Ticket ticket = user.getOwnTickets().get(0);
        List<Attachment> attachments = ticket.getTicketAttachments();
        model.addAttribute("attachments", attachments);
        return "ticket_overview";
    }
}
