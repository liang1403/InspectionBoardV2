package com.example.demo.domain.extension;

import com.example.demo.domain.Identified;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserDetailsExtended extends org.springframework.security.core.userdetails.User {

    public UserDetailsExtended(String login, String password, Collection<GrantedAuthority> grantedAuthorities, Identified connectedEntity) {
        super(login, password, grantedAuthorities);
        this.connectedEntity = connectedEntity;
    }

    @Getter
    private final Identified connectedEntity;
}
