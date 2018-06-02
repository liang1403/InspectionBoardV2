package com.example.demo.repository;

import com.example.demo.domain.Enrollee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolleeDao extends JpaRepository<Enrollee, String> {

    @Query(value = "select * from enrollee where user_id = :user_id limit 1", nativeQuery = true)
    Enrollee findByUserId(@Param("user_id") String user_id);
}
