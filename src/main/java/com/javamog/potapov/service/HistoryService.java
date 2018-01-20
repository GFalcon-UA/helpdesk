package com.javamog.potapov.service;

import com.javamog.potapov.model.history.History;
import com.javamog.potapov.model.ticket.TicketStatus;
import com.javamog.potapov.model.user.User;

import java.util.Date;

public interface HistoryService {

    History create();

    History edit();

    History statusChanged(TicketStatus previous, TicketStatus newStatus);

    History fileAttached(String filename);

    History fileRemoved(String filename);
}
