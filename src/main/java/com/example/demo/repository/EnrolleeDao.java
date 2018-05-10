package com.example.demo.repository;

import com.example.demo.domain.Enrollee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolleeDao extends JpaRepository<Enrollee, Integer> {
}
