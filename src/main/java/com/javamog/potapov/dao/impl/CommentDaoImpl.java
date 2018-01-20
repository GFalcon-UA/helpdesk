package com.javamog.potapov.dao.impl;

import com.javamog.potapov.dao.CommentDao;
import com.javamog.potapov.model.ticket.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDaoImpl implements CommentDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveComment(Comment comment) {
        getSession().save(comment);
    }

    @Override
    public void evictComment(Comment comment) {
        getSession().evict(comment);
    }
}
