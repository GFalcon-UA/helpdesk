package com.javamog.potapov.dao.Impl;

import com.google.common.base.Optional;
import com.javamog.potapov.dao.UserDAO;
import com.javamog.potapov.domain.User;
import com.javamog.potapov.parent.dao.GenericEntityDao;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends GenericEntityDao<User> implements UserDAO {
    protected UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = findBy("email", username);
        if(userOptional.isPresent()){
            return userOptional.get();
        } else {
            throw new UsernameNotFoundException(String.format("Email %s is not exist", username));
        }
    }
}
