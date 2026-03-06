package com.therivex1907.accessmanagement.dto.role;

import lombok.Data;

import java.util.Set;

@Data
public class RoleRequest {
    private String name;
    private Set<Integer> permissionsId;
}
