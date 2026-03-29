package com.therivex1907.accessmanagement.controller;

import com.therivex1907.accessmanagement.dto.AuthRequest;
import com.therivex1907.accessmanagement.dto.AuthResponse;
import com.therivex1907.accessmanagement.dto.BaseResponse;
import com.therivex1907.accessmanagement.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<AuthResponse>> login(@Valid @RequestBody AuthRequest authRequest) {
        BaseResponse<AuthResponse> response = authService.login(authRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
