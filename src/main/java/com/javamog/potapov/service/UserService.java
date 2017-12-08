package com.javamog.potapov.service;

import com.javamog.potapov.model.User;

public interface UserService {

    void saveUser(User user);

    User getUser();

    User getUser(String email);

    void updateUser(User user);

}
