package com.therivex1907.accessmanagement.repository;

import com.therivex1907.accessmanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findById(Integer id);
    Optional<Role> findByNameContainingIgnoreCase(String name);
    List<Role> findByIsActiveTrue();
}
