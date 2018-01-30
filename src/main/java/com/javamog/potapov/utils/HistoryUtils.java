package com.javamog.potapov.utils;

import com.javamog.potapov.model.History;
import com.javamog.potapov.model.Ticket;
import com.javamog.potapov.model.User;

import java.util.Date;

public class HistoryUtils {
    public static History addHistoryRecord(User user, Ticket ticket,
                                           String action, String description) {
        History history = new History();

        history.setHistoryDate(new Date());
        history.setHistoryUser(user);
        history.setHistoryTicket(ticket);
        history.setAction(action);
        history.setDescription(description);

        return history;
    }
}
