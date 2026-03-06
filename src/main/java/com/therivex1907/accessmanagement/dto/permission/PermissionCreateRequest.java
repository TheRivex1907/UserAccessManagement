package com.therivex1907.accessmanagement.dto.permission;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PermissionCreateRequest {
    @NotEmpty
    private String name;
}
