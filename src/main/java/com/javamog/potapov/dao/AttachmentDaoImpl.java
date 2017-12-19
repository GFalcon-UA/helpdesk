package com.javamog.potapov.dao;

import com.javamog.potapov.model.Attachment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AttachmentDaoImpl implements AttachmentDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveAttachment(Attachment attachment) {
        getSession().save(attachment);
    }
}
