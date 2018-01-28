package com.javamog.potapov.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = {"/"})
public class ViewController {

    @GetMapping
    public String showIndex(Model model) {
        return "index";
    }

    @GetMapping(value = "login.html")
    public String loginPage() {
        return "login/login";
    }

    @GetMapping(value = "access_denied.html")
    public String accessDenied() {
        return "access_denied";
    }

    @GetMapping(value = "ticket_list")
    public String showTicketListPage() {
        return "ticket_list";
    }

    @GetMapping(value = "ticket_overview")
    public String showTicketOverviewPage() {
        return "ticket_overview";
    }

    @GetMapping(value = "ticket/edit")
    public String editTicket() {
        return "edit";
    }


}
