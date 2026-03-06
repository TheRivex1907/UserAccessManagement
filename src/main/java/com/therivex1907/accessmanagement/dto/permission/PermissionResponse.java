package com.therivex1907.accessmanagement.dto.permission;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PermissionResponse {
    private Integer id;
    private String name;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
