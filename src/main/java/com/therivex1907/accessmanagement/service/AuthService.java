package com.therivex1907.accessmanagement.service;

import com.therivex1907.accessmanagement.dto.AuthRequest;
import com.therivex1907.accessmanagement.dto.AuthResponse;
import com.therivex1907.accessmanagement.dto.BaseResponse;

public interface AuthService {
    BaseResponse<AuthResponse> login(AuthRequest authRequest);
}
