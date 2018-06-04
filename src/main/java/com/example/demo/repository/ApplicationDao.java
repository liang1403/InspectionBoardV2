package com.example.demo.repository;

import com.example.demo.domain.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationDao extends JpaRepository<Application, String> {

    @Query(value = "select * from (" +
            "SELECT a.* FROM application a\n" +
            "JOIN subject_in_speciality sis\n" +
            "ON sis.speciality_id = :speciality_id\n" +
            "JOIN speciality s\n" +
            "ON sis.speciality_id = s.id\n" +
            "JOIN exam_result er\n" +
            "ON er.enrollee_id = a.enrollee_id AND er.subject_id = sis.subject_id\n" +
            "JOIN enrollee e\n" +
            "ON e.id = a.enrollee_id\n" +
            "GROUP BY er.enrollee_id\n" +
            "ORDER BY (avg(mark) * (1 - s.certificate_weight) + (e.certificate_average_mark * s.certificate_weight))\n" +
            "LIMIT :limit) as filtered", nativeQuery = true)
    Page<Application> findAllBySpecialityId(
            @Param("speciality_id") String speciality_id, @Param("limit") Short limit, Pageable pageable);

    Page<Application> findAllByEnrolleeId(String enrollee_id, Pageable pageable);
}

