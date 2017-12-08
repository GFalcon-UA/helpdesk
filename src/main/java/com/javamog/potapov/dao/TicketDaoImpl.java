package com.javamog.potapov.dao;

import com.javamog.potapov.model.Ticket;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl implements TicketDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveTicket(Ticket ticket) {
        sessionFactory.openSession().save(ticket);
    }

    @Override
    public void updateTicket(Ticket ticket) {
        sessionFactory.openSession().update(ticket);
    }

    @Override
    public Ticket getTicketById(int id) {
        Ticket ticket = sessionFactory.openSession().load(Ticket.class, id);
        return ticket;
    }
}
