package com.javamog.potapov.service;

import com.javamog.potapov.model.ticket.Ticket;
import com.javamog.potapov.model.ticket.TicketStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TicketService {

    Ticket getTicketById(Long id);

    void createNewTicket(Ticket ticket, String category, String dateInString,
                         MultipartFile file, String comment);

    void editTicket(Ticket ticket);

    ResponseEntity<List<Ticket>> getTickets(String username);

    void changeStatus(Ticket ticket, TicketStatus status);
}
