package com.therivex1907.accessmanagement.repository;

import com.therivex1907.accessmanagement.entity.User;
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
    SELECT u FROM User u WHERE (:lastName is null or lower(u.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) and
        (:email is null or lower(u.email) LIKE LOWER(CONCAT('%', :email, '%'))) and u.isActive = true
    """)
    List<User> searchUser(@Param("lastName")String lastName, @Param("email")String email);
    Boolean existsByEmail(String email);
    List<User> findByIsActiveTrue();
    //    Optional<User> findByLastNameContainingIgnoreCase(String name);
    //    Optional<User> findUserById(Integer id);
}
