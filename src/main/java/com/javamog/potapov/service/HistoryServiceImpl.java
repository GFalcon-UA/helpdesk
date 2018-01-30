package com.javamog.potapov.service;

import com.javamog.potapov.dao.HistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HistoryServiceImpl {

    @Autowired
    private HistoryDao historyDao;

    /*public void addHistoryRecord(Date timeStamp, User user, String action, String description) {
        History history = new History();
        history.setHistoryDate(timeStamp);
        history.setHistoryUser(user);
        history.setAction(action);
        history.setDescription(description);

    }*/
}
