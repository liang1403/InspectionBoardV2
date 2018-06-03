package com.example.demo.repository;

import com.example.demo.domain.ExamResultState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamResultStateDao extends JpaRepository<ExamResultState, String> {
}
