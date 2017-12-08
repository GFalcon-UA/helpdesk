package com.javamog.potapov.dao;

import com.javamog.potapov.model.History;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HistoryDaoImpl implements HistoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveHistory(History history) {
        sessionFactory.openSession().save(history);
    }

}


