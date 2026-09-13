package com.msvc_test.infrastructure.configuration.auth.utils;

import com.msvc_test.domain.models.User;
import com.msvc_test.infrastructure.configuration.auth.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SecurityUtils {

    public UUID getCurrentUserId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || !auth.isAuthenticated()){
            throw new RuntimeException("User is not authenticated");
        }
        return ((CustomUserDetails) auth.getPrincipal()).getId();
    }

    public User getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || !auth.isAuthenticated()){
            throw new RuntimeException("User is not authenticated");
        }
        User user = new User();
        user.setId(getCurrentUserId());
        return user;
    }

}
