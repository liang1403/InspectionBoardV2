package com.example.demo.repository;

import com.example.demo.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectDao extends JpaRepository<Subject, String> {

    @Query(value = "SELECT s.* FROM subject s\n" +
            "LEFT JOIN exam_result ex\n" +
            "ON enrollee_id = :enrollee_id AND ex.subject_id = s.id\n" +
            "WHERE ex.id IS NULL\n", nativeQuery = true)
    List<Subject> findAllByEnrolleeId(@Param("enrollee_id") String enrollee_id);
}

