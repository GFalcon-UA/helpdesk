package com.javamog.potapov.service.impl;

import com.javamog.potapov.dao.UserDAO;
import com.javamog.potapov.domain.User;
import com.javamog.potapov.domain.enums.Role;
import com.javamog.potapov.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);
//
//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDAO userDAO;

    @Override
    public User findLoggedInUser() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            String userName = ((UserDetails) userDetails).getUsername();
            return userDAO.findByUsername(userName);
        }
        return null;
    }

//    @Override
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        log.debug("loading by username = " + s);
//        return userDAO.findByUsername(s);
//    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {

        return userDAO.findByIdExpected(id);
    }

    @Override
    @Transactional
    public Role getRoleByUserId(Long id) {
        return getUserById(id).getRole();
    }

}
