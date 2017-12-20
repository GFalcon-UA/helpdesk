package com.javamog.potapov.controller;

import com.javamog.potapov.model.Category;
import com.javamog.potapov.model.FileBucket;
import com.javamog.potapov.model.Ticket;
import com.javamog.potapov.model.User;
import com.javamog.potapov.service.CategoryService;
import com.javamog.potapov.service.TicketService;
import com.javamog.potapov.service.UserService;
import com.javamog.potapov.utils.FileValidator;
import com.javamog.potapov.utils.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
@RequestMapping("/create")
public class CreateTicketController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TicketService ticketService;

   /* @Autowired
    FileValidator fileValidator;

    @InitBinder("fileBucket")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }*/

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
        List<Ticket> tickets = ticketService.createNewTicket(ticket, category, dateInString, file, commentText);
        long l = file.getSize();
        //model.addAttribute("tickets", tickets);
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
        List<Ticket> tickets = ticketService.createNewTicket(ticket, category, dateInString, file, commentText);
         model.addAttribute("tickets", tickets);
        //redirectAttributes.addFlashAttribute("tickets", tickets);
        return "redirect:/ticketList";
    }


}