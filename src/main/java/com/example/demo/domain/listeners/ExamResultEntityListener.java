package com.example.demo.domain.listeners;

import com.example.demo.domain.ExamResult;

import javax.persistence.PrePersist;

public class ExamResultEntityListener {

    @PrePersist
    public void setDefaults(ExamResult examResult) {
        EntityListenerUtilities.setExamResultDefaultOwner(examResult);
        EntityListenerUtilities.setExamResultDefaultState(examResult);
    }
}
