package com.javamog.potapov.dao.impl;

import com.javamog.potapov.dao.FeedbackDao;
import com.javamog.potapov.model.Feedback;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FeedbackDaoImpl implements FeedbackDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveFeedback(Feedback feedback) {
        getSession().save(feedback);
    }
}
