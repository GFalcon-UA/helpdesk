package com.javamog.potapov.dao.Impl;

import com.javamog.potapov.dao.UserDAO;
import com.javamog.potapov.domain.User;
import com.javamog.potapov.parent.dao.GenericEntityDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends GenericEntityDao<User> implements UserDAO {
    protected UserDaoImpl() {
        super(User.class);
    }
}
