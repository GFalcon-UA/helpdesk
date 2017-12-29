package com.javamog.potapov.dao.impl;

import com.javamog.potapov.dao.TicketDao;
import com.javamog.potapov.model.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl implements TicketDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveTicket(Ticket ticket) {
        getSession().save(ticket);
    }

    public void evictTicket(Ticket ticket) {
        getSession().evict(ticket);
    }

    @Override
    public void updateTicket(Ticket ticket) {
        getSession().merge(ticket);
    }

    @Override
    public Ticket getTicketById(Long id) {
        Ticket ticket = getSession().get(Ticket.class, id);
        return ticket;
    }
}
