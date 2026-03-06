package com.therivex1907.accessmanagement.dto.role;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class RoleCreateRequest {
    @NotBlank
    private String name;
    private Set<Integer> permissionsId;
}
