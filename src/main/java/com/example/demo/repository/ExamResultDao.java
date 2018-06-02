package com.example.demo.repository;

import com.example.demo.domain.ExamResult;
import com.example.demo.domain.ExamResultState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamResultDao extends JpaRepository<ExamResult, String> {

    @Query(value = "select * from exam_result where enrollee_id = :enrollee_id", nativeQuery = true)
    Page<ExamResult> findByEnrolleeId(@Param("enrollee_id") String enrollee_id, Pageable pageable);

    @Query(value = "select * from exam_result_state", nativeQuery = true)
    List<ExamResultState> getExamResultStateList();
}
