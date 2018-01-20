package com.javamog.potapov.service.impl;

import com.javamog.potapov.model.history.History;
import com.javamog.potapov.model.history.HistoryAction;
import com.javamog.potapov.model.ticket.TicketStatus;
import com.javamog.potapov.model.user.User;
import com.javamog.potapov.service.HistoryService;
import com.javamog.potapov.service.UserService;
import com.javamog.potapov.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Date;

@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {

    private static final String TICKET_IS_CREATED = "Ticket is created";
    private static final String FILE_IS_ATTACHED = "File is attached: {0}";
    private static final String FILE_IS_REMOVED = "File is removed: {0}";
    private static final String TICKET_IS_EDITED = "Ticket is edited";
    private static final String TICKET_STATUS_CHANGED = "Ticket status is changed from {0} to {1}";

    @Autowired
    private UserService userService;

    @Override
    public History create() {
        History history = createHistoryRecord();
        history.setAction(HistoryAction.CREATED);
        history.setDetails(TICKET_IS_CREATED);
        return history;
    }

    @Override
    public History edit() {
        History history = createHistoryRecord();
        history.setAction(HistoryAction.EDITED);
        history.setDetails(TICKET_IS_EDITED);
        return history;
    }

    @Override
    public History statusChanged(TicketStatus previous, TicketStatus newStatus) {
        History history = createHistoryRecord();
        history.setAction(HistoryAction.STATUS_CHANGED);
        history.setDetails(MessageFormat.format(TICKET_STATUS_CHANGED, previous, newStatus));
        return history;
    }

    @Override
    public History fileAttached(String filename) {
        History history = createHistoryRecord();
        history.setAction(HistoryAction.FILE_ATTACHED);
        history.setDetails(MessageFormat.format(FILE_IS_ATTACHED, filename));
        return history;
    }

    @Override
    public History fileRemoved(String filename) {
        History history = createHistoryRecord();
        history.setAction(HistoryAction.FILE_REMOVED);
        history.setDetails(MessageFormat.format(FILE_IS_REMOVED, filename));
        return history;
    }


    private History createHistoryRecord() {
        History history = new History();
        history.setDate(new Date());
        history.setUser(userService.getUser());
        return history;
    }
}
