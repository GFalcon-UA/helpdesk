package com.javamog.potapov.dao.impl;

import com.javamog.potapov.dao.TicketDao;
import com.javamog.potapov.model.ticket.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        return getSession().get(Ticket.class, id);
    }

    @Override
    public List<Ticket> getTicketsForManager(Long id) {
        String hsqlQuery = "FROM Ticket t WHERE ((t.owner.role = 'EMPLOYEE' and t.status = 'NEW') " +
                "or (t.approver.id = :approver and t.status in ('APPROVED', 'DECLINED','CANCELLED','IN_PROGRESS','DONE')))";
        Query<Ticket> query = getSession().createQuery(hsqlQuery, Ticket.class);
        query.setParameter("approver", id);
        return query.list();
    }

    @Override
    public List<Ticket> getTicketForEngineer(Long id) {
        String hsqlQuery = "FROM Ticket t WHERE ((t.owner.role = 'EMPLOYEE' or t.owner.role = 'MANAGER' and t.status = 'APPROVED') " +
                "or (t.assignee.id = :assignee and t.status in ('IN_PROGRESS','DONE')))";
        Query<Ticket> query = getSession().createQuery(hsqlQuery, Ticket.class);
        query.setParameter("assignee", id);
        return query.list();
    }
}
