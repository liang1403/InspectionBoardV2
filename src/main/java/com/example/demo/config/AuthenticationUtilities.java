package com.example.demo.config;

import com.example.demo.domain.Enrollee;
import com.example.demo.domain.Identified;
import com.example.demo.domain.extension.UserDetailsExtended;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUtilities {

    private static final String ROLE_PREFIX = "ROLE_";

    public static boolean isCurrentUserAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return isUserAuthenticationToken(auth);
    }

    public static Enrollee getCurrentEnrollee() {
        Enrollee enrollee = null;
        Identified connectedEntity = getCurrentUserConnectedEntity();
        if (connectedEntity != null && connectedEntity instanceof Enrollee) {
            enrollee = (Enrollee) connectedEntity;
        }
        return enrollee;
    }

    public static boolean isUserInRole(String role) {
        boolean hasRole = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (isUserAuthenticationToken(auth)) {
            hasRole = auth.getAuthorities().contains(new SimpleGrantedAuthority(ROLE_PREFIX + role));
        }
        return hasRole;
    }

    private static Identified getCurrentUserConnectedEntity() {
        Identified connectedEntity = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (isUserAuthenticationToken(auth)) {
            connectedEntity = ((UserDetailsExtended) auth.getPrincipal()).getConnectedEntity();
        }
        return connectedEntity;
    }

    private static boolean isUserAuthenticationToken(Authentication auth) {
        return auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated();
    }
}
