package com.javamog.potapov.controller;

import com.javamog.potapov.model.Category;
import com.javamog.potapov.model.Ticket;
import com.javamog.potapov.service.CategoryService;
import com.javamog.potapov.service.TicketService;
import com.javamog.potapov.utils.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/create")
public class CreateTicketController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TicketService ticketService;

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.getCategories();
    }

    @ModelAttribute("ticket")
    public Ticket ticket() {
        return new Ticket();
    }

    @GetMapping
    public String createTicket(Model model) {
        return "new_ticket";
    }

    @PostMapping(params = "submit")
    public String submitTicket(@ModelAttribute("ticket") Ticket ticket, BindingResult errors, Model model,
                               @RequestParam("desiredDate") String dateInString,
                               @RequestParam("comment") String commentText,
                               @RequestParam("category") String category,
                               @RequestParam("file") MultipartFile file) {

        ticket.setState(State.NEW);
        ticketService.createNewTicket(ticket, category, dateInString, file, commentText);
        return "redirect:/ticketList";
    }

    @PostMapping(params = "draft")
    public String draftTicket(@ModelAttribute("ticket") Ticket ticket, BindingResult errors, Model model,
                               @RequestParam("desiredDate") String dateInString,
                               @RequestParam("comment") String commentText,
                               @RequestParam("category") String category,
                               @RequestParam("file") MultipartFile file,
                               RedirectAttributes redirectAttributes) {

        ticket.setState(State.DRAFT);
        ticketService.createNewTicket(ticket, category, dateInString, file, commentText);
        return "redirect:/ticketList";
    }


}