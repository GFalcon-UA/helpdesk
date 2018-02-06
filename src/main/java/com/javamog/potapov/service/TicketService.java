package com.javamog.potapov.service;

import com.javamog.potapov.domain.Comment;
import com.javamog.potapov.domain.Ticket;
import com.javamog.potapov.domain.User;

import java.util.Set;

public interface TicketService {
    Set<Ticket> getTicketList(User currentUser);
    Ticket createTicket(Ticket ticket, Long userId, Long categoryId);
    Comment addComment(Ticket ticket, String comment, Long userId);


}
