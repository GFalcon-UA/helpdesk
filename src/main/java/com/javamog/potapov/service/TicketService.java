package com.javamog.potapov.service;

import com.javamog.potapov.domain.Comment;
import com.javamog.potapov.domain.Feedback;
import com.javamog.potapov.domain.Ticket;
import com.javamog.potapov.domain.User;

import java.util.Set;

public interface TicketService {
    Set<Ticket> getTicketList(User currentUser);
    Ticket saveTicket(Ticket ticket, Long userId, Long categoryId);
    Comment addComment(Ticket ticket, String comment, Long userId);
    Ticket getTicket(Long ticketId);
    Ticket setNewState(Long ticketId, Long userId, String state);
    Feedback setFeedback(Long ticketId, Long userId, Integer rate, String text);
    Feedback getFeedback(Long ticketId);
}
