package com.example.demo.domain;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserDetailsExtended extends org.springframework.security.core.userdetails.User {

    public UserDetailsExtended(String login, String password, Collection<GrantedAuthority> grantedAuthorities, Identified connectedEntity) {
        super(login, password, grantedAuthorities);
        this.connectedEntity = connectedEntity;
    }

    private Identified connectedEntity;

    public Identified getConnectedEntity() {
        return this.connectedEntity;
    }
}
