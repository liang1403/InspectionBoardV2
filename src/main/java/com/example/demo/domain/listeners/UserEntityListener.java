package com.example.demo.domain.listeners;

import com.example.demo.domain.User;

import javax.persistence.PrePersist;

public class UserEntityListener {

    @PrePersist
    public void setDefaults(User user) {
        EntityListenerUtilities.setUserEncodedPassword(user);
        EntityListenerUtilities.setUserDefaultRole(user);
    }
}
