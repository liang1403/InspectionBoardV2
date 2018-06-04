package com.example.demo.service;

import com.example.demo.domain.Subject;
import com.example.demo.repository.SubjectDao;
import com.example.demo.service.base.EntityServiceImpl;
import com.example.demo.service.interfaces.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl extends EntityServiceImpl<Subject, String> implements ISubjectService {

    private final SubjectDao subjectDao;

    @Autowired
    public SubjectServiceImpl(SubjectDao repo) {
        super(repo);
        this.subjectDao = repo;
    }

    public List<Subject> findAllByEnrolleeId(String enrollee_id) {
        return subjectDao.findAllByEnrolleeId(enrollee_id);
    }
}