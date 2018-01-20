package com.javamog.potapov.dao;

import com.javamog.potapov.model.user.User;

import java.util.List;

public interface UserDao {

    void saveUser(User user);

    User getUser(String email);

    void updateUser(User user);

    List<User> getAll();
}
