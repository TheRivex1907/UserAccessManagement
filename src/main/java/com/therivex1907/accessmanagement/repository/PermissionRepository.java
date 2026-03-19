package com.therivex1907.accessmanagement.repository;

import com.therivex1907.accessmanagement.entity.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    Optional<Permission> findById(Integer id);
    Optional<Permission> findByNameIgnoreCase(String name);
    Page<Permission> findByIsActiveTrue(Pageable pageable);
}
