package com.javamog.potapov.utils;

import com.javamog.potapov.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserUtils {

    public static String getLoggedInUserEmail() {
        String email = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        email = ((UserDetails)principal).getUsername();

        //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        return email;
    }
}
