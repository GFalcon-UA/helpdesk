package com.javamog.potapov.service.impl;

import com.javamog.potapov.dao.HistoryDAO;
import com.javamog.potapov.domain.History;
import com.javamog.potapov.domain.Ticket;
import com.javamog.potapov.domain.User;
import com.javamog.potapov.domain.enums.State;
import com.javamog.potapov.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryDAO historyDAO;

    @Override
    public List<History> getHistoriesByTicket(Ticket ticket) {
        return historyDAO.findAllBy("ticket", ticket);
    }

    @Override
    public void createTicket(User user, Ticket ticket) {
        History history = new History();
        history.setAction("Ticket is created");
        history.setDescription("Ticket is created");
        user.addHistories(history);
        ticket.addHistories(history);
        historyDAO.saveOrUpdate(history);
    }

    @Override
    public void editTicket(User user, Ticket ticket) {
        History history = new History();
        history.setAction("Ticket is edited");
        history.setDescription("Ticket is edited");
        user.addHistories(history);
        ticket.addHistories(history);
        historyDAO.saveOrUpdate(history);
    }

    @Override
    public void changeStatus(User user, Ticket ticket, State oldState) {
        History history = new History();
        history.setAction("Ticket Status is changed");
        history.setDescription(String.format("Ticket Status is changed from %s to %s", oldState.getStatus(), ticket.getState().getStatus()));
        user.addHistories(history);
        ticket.addHistories(history);
        historyDAO.saveOrUpdate(history);
    }

    @Override
    public void attachFile(User user, Ticket ticket, String fileName) {
        History history = new History();
        history.setAction("File is attached");
        history.setDescription("File is attached: " + fileName);
        user.addHistories(history);
        ticket.addHistories(history);
        historyDAO.saveOrUpdate(history);
    }

    @Override
    public void removeFile(User user, Ticket ticket, String fileName) {
        History history = new History();
        history.setAction("File is removed");
        history.setDescription("File is removed: " + fileName);
        user.addHistories(history);
        ticket.addHistories(history);
        historyDAO.saveOrUpdate(history);
    }
}
