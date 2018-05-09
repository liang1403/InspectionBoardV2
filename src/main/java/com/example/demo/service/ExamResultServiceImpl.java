package com.example.demo.service;

import com.example.demo.domain.ExamResult;
import com.example.demo.repository.ExamResultDao;
import com.example.demo.service.base.EntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamResultServiceImpl extends EntityServiceImpl<ExamResult, String> {

    @Autowired
    public ExamResultServiceImpl(ExamResultDao repo) {
        super(repo);
    }
}