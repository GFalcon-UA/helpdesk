package com.javamog.potapov.controller;

import com.javamog.potapov.model.ticket.Attachment;
import com.javamog.potapov.model.ticket.Ticket;
import com.javamog.potapov.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.util.List;

@Controller
@RequestMapping("/ticketOverview")
public class TicketOverviewController {

    @Autowired
    private TicketService ticketService;

    @ModelAttribute("id")
    public String getId(@PathVariable("id") String id) {
        return id;
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
