package com.javamog.potapov.dao;

import com.javamog.potapov.model.Comment;
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
        //sessionFactory.openSession().save(comment);
        getSession().save(comment);
        //sessionFactory.getCurrentSession().save(comment);
    }
}
