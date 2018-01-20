package com.javamog.potapov.controller;

import com.javamog.potapov.model.ticket.Ticket;
import com.javamog.potapov.model.ticket.TicketCategory;
import com.javamog.potapov.model.ticket.TicketStatus;
import com.javamog.potapov.model.user.User;
import com.javamog.potapov.service.AttachmentService;
import com.javamog.potapov.service.TicketService;
import com.javamog.potapov.service.UserService;
import com.javamog.potapov.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Autowired
    private AttachmentService attachmentService;

    @ModelAttribute("users")
    public List<User> users() {
        return userService.getAll();
    }

    @ModelAttribute("categories")
    public List<TicketCategory> categories() {
        return Arrays.asList(TicketCategory.values());
    }

    @ModelAttribute("ticket")
    public Ticket ticket() {
        return new Ticket();
    }

    @GetMapping(value = "/create")
    public String createTicket() {
        return "create_ticket";
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Ticket>> getTickets() {
        return ticketService.getTickets(UserUtils.getCurrentUser().getUsername());
    }

    @PostMapping(value = "/create", params = "submit")
    public RedirectView submitTicket(@ModelAttribute("ticket") Ticket ticket, BindingResult errors, Model model,
                                     @RequestParam("desiredDate") String dateInString,
                                     @RequestParam("comment") String commentText,
                                     @RequestParam("category") String category,
                                     @RequestParam("file") MultipartFile file) {

        ticketService.createNewTicket(ticket, category, dateInString, file, commentText);
        return getCleanRedirectView("/");
    }

    @PostMapping(params = "draft")
    public RedirectView draftTicket(@ModelAttribute("ticket") Ticket ticket, BindingResult errors, Model model,
                                    @RequestParam("desiredDate") String dateInString,
                                    @RequestParam("comment") String commentText,
                                    @RequestParam("category") String category,
                                    @RequestParam("file") MultipartFile file,
                                    RedirectAttributes redirectAttributes) throws ParseException {
        ticket.setStatus(TicketStatus.DRAFT);
        ticketService.createNewTicket(ticket, category, dateInString, file, commentText);
        return getCleanRedirectView("/");
    }


    @GetMapping("/{id}")
    public ResponseEntity<Ticket> showOverviewPage(@PathVariable("id") Long id) {
        Ticket ticket = ticketService.getTicketById(id);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PostMapping(value = "/edit")
    public RedirectView saveTicket(@RequestBody Ticket ticket) {
        ticketService.editTicket(ticket);
        return getCleanRedirectView("redirect:/");
    }

    @GetMapping(value = "/delete")
    public RedirectView deleteAttachment(@ModelAttribute("ticket") Ticket ticket) {
        User user = ticket.getOwner();
        /*Attachment attachment = ticket.getTicketAttachments().get(0);
        attachmentService.deleteAttachment(attachment, user, ticket);*/
        return getCleanRedirectView("redirect:/edit");
    }

    private RedirectView getCleanRedirectView(String url) {
        RedirectView redirectView = new RedirectView(url);
        redirectView.setExposeModelAttributes(false);
        return redirectView;
    }


}