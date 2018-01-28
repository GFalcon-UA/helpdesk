package com.javamog.potapov.dao;

import com.javamog.potapov.model.ticket.Ticket;

import java.util.List;

public interface TicketDao {

    void saveTicket(Ticket ticket);

    void updateTicket(Ticket ticket);

    void evictTicket(Ticket ticket);

    Ticket getTicketById(Long id);

    List<Ticket> getTicketsForManager(Long id);

    List<Ticket> getTicketForEngineer(Long id);
}
