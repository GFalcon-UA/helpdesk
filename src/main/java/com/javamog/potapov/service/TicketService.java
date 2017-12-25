package com.javamog.potapov.service;

import com.javamog.potapov.model.FileBucket;
import com.javamog.potapov.model.Ticket;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TicketService {

    Ticket getTicketById(int id);

    public void createNewTicket(Ticket ticket, String category, String dateInString,
                                        MultipartFile file, String comment);

    public List<Ticket> editTicket(Ticket ticket, String category, String dateInString,
                                        MultipartFile file, String comment);
}
