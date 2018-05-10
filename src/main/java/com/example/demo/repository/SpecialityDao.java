package com.example.demo.repository;

import com.example.demo.domain.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialityDao extends JpaRepository<Speciality, Integer> {
}
