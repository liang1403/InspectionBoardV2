package com.example.demo.service;

import com.example.demo.domain.Subject;
import com.example.demo.repository.SubjectDao;
import com.example.demo.service.base.EntityServiceImpl;
import com.example.demo.service.interfaces.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl extends EntityServiceImpl<Subject, Integer> implements ISubjectService {

    @Autowired
    public SubjectServiceImpl(SubjectDao repo) {
        super(repo);
    }
}