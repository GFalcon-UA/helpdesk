/*
 *  MIT License
 * -----------
 *
 * Copyright (c) 2016-2019 Oleksii V. KHALIKOV, PE (gfalcon.com.ua)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ua.com.gfalcon.helpdesk.service.impl;

import ua.com.gfalcon.helpdesk.dao.UserDAO;
import ua.com.gfalcon.helpdesk.domain.User;
import ua.com.gfalcon.helpdesk.domain.enums.Role;
import ua.com.gfalcon.helpdesk.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@Transactional
public class UserServiceImpl implements UserService {

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


    @Override
    @Transactional(readOnly = true)
    public List<User> getUsersByRole(Role role) {
        return userDAO.findAllBy("role", role);
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
    @Transactional(readOnly = true)
    public Role getRoleByUserId(Long id) {
        return getUserById(id).getRole();
    }

}
