package com.javamog.potapov.controller;

import com.javamog.potapov.model.Category;
import com.javamog.potapov.model.Ticket;
import com.javamog.potapov.model.User;
import com.javamog.potapov.service.CategoryService;
import com.javamog.potapov.service.TicketService;
import com.javamog.potapov.service.UserService;
import com.javamog.potapov.utils.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/edit")
public class EditTicketController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.getCategories();
    }

    @ModelAttribute("ticket")
    public Ticket ticket() {
        User user = userService.getUser("user1_mogilev@yopmail.com");
        Ticket ticket = user.getOwnTickets().get(0);
        return ticket;
        //return new Ticket();
    }

    @GetMapping
    public String editTicket(Model model) {
        return "edit";
    }

    @PostMapping(params = "submit")
    public String submitTicket(@ModelAttribute("ticket") Ticket ticket, BindingResult errors, Model model,
                               @RequestParam("desiredDate") String dateInString,
                               @RequestParam("comment") String commentText,
                               @RequestParam("category") String category,
                               @RequestParam("file") MultipartFile file) {

        ticket.setState(State.NEW);
        List<Ticket> tickets = ticketService.editTicket(ticket, category, dateInString, file, commentText);
        //model.addAttribute("tickets", tickets);
        //return "hello";
        return "redirect:/ticketOverview";
        //return "ticketOverview";
        //return "page3";
    }

    @PostMapping(params = "save")
    public String saveTicket(@ModelAttribute("ticket") Ticket ticket, BindingResult errors, Model model,
                               @RequestParam("desiredDate") String dateInString,
                               @RequestParam("comment") String commentText,
                               @RequestParam("category") String category,
                               @RequestParam("file") MultipartFile file) {

        List<Ticket> tickets = ticketService.editTicket(ticket, category, dateInString, file, commentText);
        model.addAttribute("tickets", tickets);
        return "hello";
        //return "redirect:/hello";
        //return "page3";
    }
}
