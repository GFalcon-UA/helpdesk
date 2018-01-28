package com.javamog.potapov.dao.impl;

import com.javamog.potapov.dao.UserDao;
import com.javamog.potapov.model.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveUser(User user) {
        getSession().save(user);
    }

    @Override
    public User getUser(String email) {
        return (User) getSession()
                .createQuery("FROM User where email = :email")
                .setParameter("email", email).getSingleResult();
    }

    @Override
    public void updateUser(User user) {
        getSession().update(user);
    }

    @Override
    public List<User> getAll() {
        return getSession().createQuery("FROM User", User.class).getResultList();
    }
}
