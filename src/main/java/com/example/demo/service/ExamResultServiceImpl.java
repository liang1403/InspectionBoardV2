package com.example.demo.service;

import com.example.demo.domain.ExamResult;
import com.example.demo.domain.ExamResultState;
import com.example.demo.repository.ExamResultDao;
import com.example.demo.repository.ExamResultStateDao;
import com.example.demo.service.base.EntityServiceImpl;
import com.example.demo.service.interfaces.IExamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.Constants.EXAM_RESULT_STATE_NOT_CHECKED;

@Service
public class ExamResultServiceImpl extends EntityServiceImpl<ExamResult, String> implements IExamResultService {

    private final ExamResultStateDao examResultStateDao;

    private final ExamResultDao examResultDao;

    @Autowired
    public ExamResultServiceImpl(ExamResultDao repo, ExamResultStateDao examResultStateDao) {
        super(repo);
        this.examResultDao = repo;
        this.examResultStateDao = examResultStateDao;
    }

    @Override
    public ExamResult create(ExamResult examResult) {
        this.setDefaultState(examResult);
        return super.create(examResult);
    }

    private void setDefaultState(ExamResult examResult) {
        examResult.setState(examResultStateDao.getOne(EXAM_RESULT_STATE_NOT_CHECKED));
    }

    public Page<ExamResult> findAllByEnrolleeId(String enrollee_id, Pageable pageable) {
        return examResultDao.findAllByEnrolleeId(enrollee_id, pageable);
    }

    public List<ExamResultState> findAllExamResultStates() {
        return examResultStateDao.findAll();
    }
}