package com.javamog.potapov.service;

import com.javamog.potapov.domain.History;
import com.javamog.potapov.domain.Ticket;
import com.javamog.potapov.domain.User;
import com.javamog.potapov.domain.enums.State;

import java.util.List;

public interface HistoryService {

    List<History> getHistoriesByTicket(Ticket ticket);

    void createTicket(User user, Ticket ticket);
    void editTicket(User user, Ticket ticket);
    void changeStatus(User user, Ticket ticket, State oldState);
    void attachFile(User user, Ticket ticket, String fileName);
    void removeFile(User user, Ticket ticket, String fileName);

}
