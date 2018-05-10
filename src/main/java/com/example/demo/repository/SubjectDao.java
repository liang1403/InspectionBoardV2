package com.example.demo.repository;

import com.example.demo.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectDao extends JpaRepository<Subject, Integer> {
}
