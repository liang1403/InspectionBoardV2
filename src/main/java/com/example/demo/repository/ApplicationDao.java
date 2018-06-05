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


    @Query(value = "SELECT * FROM (" +
            "SELECT a.* FROM application a\n" +
            "WHERE a.speciality_id = :speciality_id\n" +
            "ORDER BY a.point\n" +
            "LIMIT :limit) AS filtered", nativeQuery = true)
    Page<Application> findAllBySpecialityIdOrderByPoint(
            @Param("speciality_id") String speciality_id, @Param("limit") Short limit, Pageable pageable);

    Page<Application> findAllByEnrolleeId(String enrollee_id, Pageable pageable);
}

