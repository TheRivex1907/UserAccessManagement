package com.therivex1907.accessmanagement.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UserResponse {
    private Integer id;
    private String email;
    private String phoneNumber;
    private Set<Integer> rolesId;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
