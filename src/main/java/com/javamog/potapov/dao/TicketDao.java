package com.javamog.potapov.dao;

import com.javamog.potapov.model.Ticket;

public interface TicketDao {

    void saveTicket(Ticket ticket);

    void updateTicket(Ticket ticket);

    void evictTicket(Ticket ticket);

    Ticket getTicketById(Long id);
}
