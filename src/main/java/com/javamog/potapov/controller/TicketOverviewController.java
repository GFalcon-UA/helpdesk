package com.javamog.potapov.controller;

import com.javamog.potapov.model.Attachment;
import com.javamog.potapov.model.Ticket;
import com.javamog.potapov.model.User;
import com.javamog.potapov.service.TicketService;
import com.javamog.potapov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.util.List;

@Controller
@RequestMapping("/ticketOverview")
public class TicketOverviewController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String showTicketOverviewPage(Model model) {
        User user = userService.getUser("user1_mogilev@yopmail.com");
        Ticket ticket = user.getOwnTickets().get(0);
        List<Attachment> attachments = ticket.getTicketAttachments();
        model.addAttribute("attachments", attachments);
        return "ticket_overview";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downloadAttachment(HttpServletResponse response) throws IOException {

        File file = new File("D:/folder/New Text Document.txt");
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        response.setContentLength((int) file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        FileCopyUtils.copy(inputStream, response.getOutputStream());

    }
}
