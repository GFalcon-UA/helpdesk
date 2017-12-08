package com.javamog.potapov.dao;

import com.javamog.potapov.model.Comment;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDaoImpl implements CommentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveComment(Comment comment) {
        sessionFactory.openSession().save(comment);
    }
}
