package com.example.demo.service;

import com.example.demo.domain.ExamResult;
import com.example.demo.domain.ExamResultState;
import com.example.demo.repository.ExamResultDao;
import com.example.demo.service.base.EntityServiceImpl;
import com.example.demo.service.interfaces.IExamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ExamResultServiceImpl extends EntityServiceImpl<ExamResult, String> implements IExamResultService {

    private ExamResultDao examResultDao;

    @Autowired
    public ExamResultServiceImpl(ExamResultDao repo) {
        super(repo);
        this.examResultDao = repo;
    }

    public Page<ExamResult> findByEnrolleeId(String enrollee_id, Pageable pageable) {
        return examResultDao.findByEnrolleeId(enrollee_id, pageable);
    }

    public List<ExamResultState> getExamResultStateList() {
        return examResultDao.getExamResultStateList();
    }
}