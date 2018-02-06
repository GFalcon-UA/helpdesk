package com.javamog.potapov.dao;

import com.javamog.potapov.domain.User;
import com.javamog.potapov.parent.dao.EntityDao;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Oleksii Khalikov
 * @version 1.0.0
 * @since 1.0.0
 */
public interface UserDAO extends EntityDao<User> {
    User findByUsername(String username) throws UsernameNotFoundException;
}
