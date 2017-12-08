package com.javamog.potapov.dao;

import com.javamog.potapov.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void saveUser(User user) {
        sessionFactory.openSession().save(user);
    }

    @Override
    public User getUser() {
        User user = (User) sessionFactory.openSession().createQuery("FROM User").getSingleResult();
        return user;
    }

    @Override
    public User getUser(String email) {
        User user = (User) sessionFactory.openSession()
                .createQuery( "FROM User where email = :email")
                .setParameter("email", email).getSingleResult();
        return user;
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.openSession().merge(user);
    }
}
