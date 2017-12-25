package com.javamog.potapov.dao;

import com.javamog.potapov.model.User;

public interface UserDao {

    void saveUser(User user);

    User getUser();

    User getUser(String email);

    void evictUser(User user);

    void updateUser(User user);

}
