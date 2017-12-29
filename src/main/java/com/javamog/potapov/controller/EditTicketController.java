package com.javamog.potapov.controller;

import com.javamog.potapov.model.Attachment;
import com.javamog.potapov.model.Category;
import com.javamog.potapov.model.Ticket;
import com.javamog.potapov.model.User;
import com.javamog.potapov.service.AttachmentService;
import com.javamog.potapov.service.CategoryService;
import com.javamog.potapov.service.TicketService;
import com.javamog.potapov.utils.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/edit/{id}")
public class EditTicketController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private AttachmentService attachmentService;

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.getCategories();
    }

    @ModelAttribute("ticket")
    public Ticket ticket(@PathVariable("id") Long id) {
        return ticketService.getTicketById(id);
    }

    @ModelAttribute("id")
    public String getId(@PathVariable("id") String id) {
        return id;
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
        return "redirect:/ticketOverview/{id}";
    }

    @PostMapping(params = "save")
    public String saveTicket(@ModelAttribute("ticket") Ticket ticket, BindingResult errors, Model model,
                               @RequestParam("desiredDate") String dateInString,
                               @RequestParam("comment") String commentText,
                               @RequestParam("category") String category,
                               @RequestParam("file") MultipartFile file) {

        List<Ticket> tickets = ticketService.editTicket(ticket, category, dateInString, file, commentText);
        return "redirect:/ticketOverview/{id}";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteAttachment(@ModelAttribute("ticket") Ticket ticket) {
        User user = ticket.getOwner();
        Attachment attachment = ticket.getTicketAttachments().get(0);
        attachmentService.deleteAttachment(attachment, user, ticket);
        return "redirect:/edit";
    }

}
