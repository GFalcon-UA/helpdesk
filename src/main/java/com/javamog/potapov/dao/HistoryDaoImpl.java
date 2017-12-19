package com.javamog.potapov.dao;

import com.javamog.potapov.model.History;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HistoryDaoImpl implements HistoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveHistory(History history) {
        getSession().save(history);
    }

}


