package com.example.demo.repository;

import com.example.demo.domain.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamResultDao extends JpaRepository<ExamResult, Integer> {
}
