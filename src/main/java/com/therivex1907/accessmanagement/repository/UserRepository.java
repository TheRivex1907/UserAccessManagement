package com.therivex1907.accessmanagement.repository;

import com.therivex1907.accessmanagement.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(Integer id);
    @Query("""
    SELECT u FROM User u WHERE (:lastName is null or lower(u.lastName) LIKE :lastName) and
        (:email is null or lower(u.email) LIKE :email)
    """)
    Page<User> searchUser(@Param("lastName")String lastName, @Param("email")String email, Pageable pageable);
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    //Page<User> findByIsActiveTrue();
    //    Optional<User> findByLastNameContainingIgnoreCase(String name);
    //    Optional<User> findUserById(Integer id);
}
