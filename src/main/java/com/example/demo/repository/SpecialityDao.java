package com.example.demo.repository;

import com.example.demo.domain.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialityDao extends JpaRepository<Speciality, String> {

    String EXAM_RESULT_STATE_CONFIRMED = "a333aabb-6686-11e8-afa7-444553544f53";

    @Query(value = "SELECT s.* FROM speciality s \n" +
            "JOIN subject_in_speciality sis\n" +
            "ON sis.speciality_id = s.id\n" +
            "LEFT JOIN application a\n" +
            "ON a.enrollee_id = :enrollee_id AND\n" +
            "a.speciality_id = s.id\n" +
            "LEFT JOIN exam_result er\n" +
            "ON er.enrollee_id = :enrollee_id AND\n" +
            "er.subject_id = sis.subject_id AND \n" +
            "er.state_id = '" + EXAM_RESULT_STATE_CONFIRMED + "'\n" +
            "WHERE a.id IS NULL\n" +
            "GROUP BY s.id\n" +
            "HAVING count(sis.subject_id) = count(er.id)", nativeQuery = true)
    List<Speciality> findAllByEnrolleeId(@Param("enrollee_id") String enrollee_id);
}
