package com.javamog.potapov.dao.impl;

import com.javamog.potapov.dao.UserDao;
import com.javamog.potapov.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveUser(User user) {
        //sessionFactory.openSession().save(user);
        getSession().save(user);
        //sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void evictUser(User user){
        getSession().evict(user);
    }

    @Override
    public User getUser() {
        //User user = (User) sessionFactory.openSession().createQuery("FROM User").getSingleResult();
        User user = (User) getSession().createQuery("FROM User").getSingleResult();
        //User user = (User) sessionFactory.getCurrentSession().createQuery("FROM User").getSingleResult();
        return user;
    }

    @Override
    public User getUser(String email) {
        /*User user = (User) sessionFactory.openSession()
                .createQuery( "FROM User where email = :email")
                .setParameter("email", email).getSingleResult();*/
        User user = (User) getSession()
                .createQuery( "FROM User where email = :email")
                .setParameter("email", email).getSingleResult();

        /*User user = (User) sessionFactory.getCurrentSession()
                .createQuery( "FROM User where email = :email")
                .setParameter("email", email).getSingleResult();*/
        return user;
    }

    @Override
    public void updateUser(User user) {
        //sessionFactory.openSession().merge(user);
        getSession().update(user);
        //sessionFactory.getCurrentSession().update(user);
    }
}
