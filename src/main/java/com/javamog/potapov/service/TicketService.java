package com.javamog.potapov.service;

import com.javamog.potapov.domain.Comment;
import com.javamog.potapov.domain.Feedback;
import com.javamog.potapov.domain.Ticket;
import com.javamog.potapov.domain.User;
import com.javamog.potapov.dto.models.FeedbackDTO;
import com.javamog.potapov.dto.models.TicketDTO;

import java.util.Set;

public interface TicketService {
    Set<Ticket> getTicketList(User currentUser);
    Ticket saveTicket(TicketDTO ticket, Long userId, Long categoryId);
    Comment addComment(Ticket ticket, String comment, Long userId);
    Ticket getTicket(Long ticketId);
    Ticket setNewState(Long ticketId, Long userId, String state);
    Feedback setFeedback(Long ticketId, Long userId, FeedbackDTO feedback);
    Feedback getFeedback(Long ticketId);
}
