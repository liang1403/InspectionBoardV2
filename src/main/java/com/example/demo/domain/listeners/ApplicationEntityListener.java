package com.example.demo.domain.listeners;

import com.example.demo.domain.Application;

import javax.persistence.PrePersist;

public class ApplicationEntityListener {

    @PrePersist
    public void setDefaults(Application application) {
        EntityListenerUtilities.setApplicationDefaultOwner(application);
        EntityListenerUtilities.setApplicationPoint(application);
    }
}
