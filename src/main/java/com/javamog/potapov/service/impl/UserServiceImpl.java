package com.javamog.potapov.service.impl;

import com.javamog.potapov.dao.UserDao;
import com.javamog.potapov.model.user.User;
import com.javamog.potapov.service.UserService;
import com.javamog.potapov.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User getUser(){
        return userDao.getUser(UserUtils.getCurrentUser().getUsername());
    }

    @Override
    public User getUser(String email) {
        return userDao.getUser(email);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
