package com.therivex1907.accessmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class RoleRequest {
    @NotBlank
    private String name;
    private Set<Integer> permissionsId;
}
