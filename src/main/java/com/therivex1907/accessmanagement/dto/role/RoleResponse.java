package com.therivex1907.accessmanagement.dto.role;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class RoleResponse {
    private Integer id;
    private String name;
    private Set<Integer> permissions;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
