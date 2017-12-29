package com.javamog.potapov.configuration.security;

import com.javamog.potapov.model.User;
import com.javamog.potapov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @Transactional(readOnly=true)
    @Override
    //TODO: Don't add throws for unchecked exceptions
    public UserDetails loadUserByUsername(String username) {
        User user = userService.getUser(username);
        if(user==null){
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), getGrantedAuthorities(user));
    }


    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRoleId()));
        return authorities;
    }

}
