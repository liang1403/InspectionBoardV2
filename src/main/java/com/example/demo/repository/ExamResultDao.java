package com.example.demo.repository;

import com.example.demo.domain.ExamResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamResultDao extends JpaRepository<ExamResult, String> {

    Page<ExamResult> findAllByEnrolleeId(String enrollee_id, Pageable pageable);

    Long countByEnrolleeId(String enrollee_id);
}
