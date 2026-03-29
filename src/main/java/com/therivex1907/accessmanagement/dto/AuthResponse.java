package com.therivex1907.accessmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthResponse {
    private String token;
    private String type;
    private AuthUserResponse user;
}
