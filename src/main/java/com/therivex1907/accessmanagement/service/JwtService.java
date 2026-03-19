package com.therivex1907.accessmanagement.service;

import com.therivex1907.accessmanagement.entity.User;

public interface JwtService {
    String generateToken(User user);
    String extractUsername(String token);
}
