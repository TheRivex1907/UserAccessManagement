package com.therivex1907.accessmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserResponse {
    private Integer id;
    private String email;
    private String name;
    private Set<String> roles;
    private Set<String> permissions;
}
