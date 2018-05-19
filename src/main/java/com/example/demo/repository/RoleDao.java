package com.example.demo.repository;

import com.example.demo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, String> {

    @Query(value = "select * from role u where name = ?1 limit 1", nativeQuery = true)
    Role findByName(String roleName);
}
