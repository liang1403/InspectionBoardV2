package com.example.demo.repository;

import com.example.demo.domain.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyDao extends JpaRepository<Faculty, Integer> {
}
