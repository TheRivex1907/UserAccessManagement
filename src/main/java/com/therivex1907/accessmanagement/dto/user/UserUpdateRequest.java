package com.therivex1907.accessmanagement.dto.user;

import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.Set;

@Data
public class UserUpdateRequest {
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String password;
    private String phoneNumber;
    private Set<Integer> rolesId;
}
