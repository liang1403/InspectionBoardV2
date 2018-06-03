package com.example.demo.service;

import com.example.demo.config.AuthenticationUtilities;
import com.example.demo.domain.Enrollee;
import com.example.demo.domain.ExamResult;
import com.example.demo.domain.ExamResultState;
import com.example.demo.domain.Subject;
import com.example.demo.repository.ExamResultDao;
import com.example.demo.repository.ExamResultStateDao;
import com.example.demo.repository.SubjectDao;
import com.example.demo.service.base.EntityServiceImpl;
import com.example.demo.service.interfaces.IExamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ExamResultServiceImpl extends EntityServiceImpl<ExamResult, String> implements IExamResultService {

    @Autowired
    private ExamResultStateDao examResultStateDao;

    @Autowired
    private SubjectDao subjectDao;

    private ExamResultDao examResultDao;

    @Autowired
    public ExamResultServiceImpl(ExamResultDao repo) {
        super(repo);
        this.examResultDao = repo;
    }

    @Override
    public ExamResult create(ExamResult examResult) {
        this.setDefaultOwner(examResult);
        return super.create(examResult);
    }

    private void setDefaultOwner(ExamResult examResult) {
        Enrollee enrollee = AuthenticationUtilities.getCurrentEnrollee();
        if (Objects.isNull(enrollee)) {

        }
        examResult.setEnrollee(enrollee);
    }

    public Page<ExamResult> findAllByEnrolleeId(String enrollee_id, Pageable pageable) {
        return examResultDao.findAllByEnrolleeId(enrollee_id, pageable);
    }

    public List<ExamResultState> getExamResultStateList() {
        return examResultStateDao.findAll();
    }

    public List<Subject> getSubjectList() {
        return subjectDao.findAll();
    }
}