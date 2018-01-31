package com.javamog.potapov.dao.Impl;

import com.javamog.potapov.dao.TicketDAO;
import com.javamog.potapov.domain.Ticket;
import com.javamog.potapov.parent.dao.GenericEntityDao;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl extends GenericEntityDao<Ticket> implements TicketDAO {
    protected TicketDaoImpl() {
        super(Ticket.class);
    }
}
