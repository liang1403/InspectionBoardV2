package com.example.demo.config;

import com.example.demo.domain.Enrollee;
import com.example.demo.domain.Identified;
import com.example.demo.domain.UserDetailsExtended;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUtilities {

    public static boolean isCurrentUserAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated();
    }

    public static Enrollee getCurrentEnrollee() {
        Enrollee enrollee = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) {
            Identified principal = ((UserDetailsExtended) auth.getPrincipal()).getConnectedEntity();
            if (principal instanceof Enrollee) {
                enrollee = (Enrollee) principal;
            }
        }
        return enrollee;
    }
}
