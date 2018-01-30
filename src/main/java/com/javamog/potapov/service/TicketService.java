package com.javamog.potapov.service;

import com.javamog.potapov.model.Ticket;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TicketService {
    public void saveTicket(Ticket ticket);

    public List<Ticket> createNewTicket(Ticket ticket, String category, String dateInString,
                                        MultipartFile file, String comment);

    public List<Ticket> editTicket(Ticket ticket, String category, String dateInString,
                                        MultipartFile file, String comment);
}
