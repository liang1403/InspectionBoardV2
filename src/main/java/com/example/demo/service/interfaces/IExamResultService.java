package com.example.demo.service.interfaces;

import com.example.demo.domain.ExamResult;
import com.example.demo.domain.ExamResultState;
import com.example.demo.service.base.IEntityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IExamResultService extends IEntityService<ExamResult, String> {

    Page<ExamResult> findAllByEnrolleeId(String enrollee_id, Pageable pageable);

    List<ExamResultState> findAllExamResultStates();
}
