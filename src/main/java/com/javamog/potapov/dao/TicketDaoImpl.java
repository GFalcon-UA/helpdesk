package com.javamog.potapov.dao;

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
        //sessionFactory.openSession().save(ticket);
        getSession().save(ticket);
        //sessionFactory.getCurrentSession().save(ticket);
    }

    public void evictTicket(Ticket ticket) {
        //sessionFactory.openSession().evict(ticket);
        getSession().evict(ticket);
        //sessionFactory.getCurrentSession().save(ticket);
    }

    @Override
    public void updateTicket(Ticket ticket) {
        //sessionFactory.openSession().merge(ticket);
        getSession().merge(ticket);
        //sessionFactory.getCurrentSession().update(ticket);
    }

    @Override
    public Ticket getTicketById(int id) {
        //Ticket ticket = sessionFactory.openSession().load(Ticket.class, id);
        Ticket ticket = getSession().load(Ticket.class, id);
        //Ticket ticket = sessionFactory.getCurrentSession().load(Ticket.class, id);
        return ticket;
    }
}
