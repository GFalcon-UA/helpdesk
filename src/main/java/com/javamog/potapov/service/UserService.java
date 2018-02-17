package com.javamog.potapov.service;

import com.javamog.potapov.domain.User;
import com.javamog.potapov.domain.enums.Role;

import java.util.List;

public interface UserService {

    User getUserById(Long id);
    Role getRoleByUserId(Long id);
    User findLoggedInUser();
    List<User> getUsersByRole(Role role);

}
