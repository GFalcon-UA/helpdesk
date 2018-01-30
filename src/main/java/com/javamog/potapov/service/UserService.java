package com.javamog.potapov.service;

import com.javamog.potapov.model.Ticket;
import com.javamog.potapov.model.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    User getUser();

    User getUser(String email);

    void updateUser(User user);

    List<Ticket> getUserTickets(User user);

}
