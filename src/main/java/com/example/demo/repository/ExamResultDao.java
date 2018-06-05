package com.example.demo.repository;

import com.example.demo.domain.ExamResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamResultDao extends JpaRepository<ExamResult, String> {

    Page<ExamResult> findAllByEnrolleeId(String enrollee_id, Pageable pageable);

    List<ExamResult> getExamResultsByEnrolleeId(String enrollee_id);
}
