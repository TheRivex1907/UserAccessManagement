package com.therivex1907.accessmanagement.service.impl;

import com.therivex1907.accessmanagement.dto.AuthRequest;
import com.therivex1907.accessmanagement.dto.AuthResponse;
import com.therivex1907.accessmanagement.dto.AuthUserResponse;
import com.therivex1907.accessmanagement.dto.BaseResponse;
import com.therivex1907.accessmanagement.entity.Permission;
import com.therivex1907.accessmanagement.entity.Role;
import com.therivex1907.accessmanagement.entity.User;
import com.therivex1907.accessmanagement.exception.ResourceNotFoundException;
import com.therivex1907.accessmanagement.repository.UserRepository;
import com.therivex1907.accessmanagement.service.AuthService;
import com.therivex1907.accessmanagement.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private AuthenticationManager getAuthManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(provider);
    }

    @Override
    public BaseResponse<AuthResponse> login(AuthRequest authRequest){
        getAuthManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );

        User user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        String token = jwtService.generateToken(user);

        AuthUserResponse authUser = AuthUserResponse.builder()
                .id(user.getId())
                .name(user.getFirstName() + " " + user.getLastName())
                .email(user.getEmail())
                .roles(user.getRolesAssigned().stream().filter(Role::getIsActive).map(Role::getName).collect(Collectors.toSet()))
                .permissions(user.getRolesAssigned().stream().filter(Role::getIsActive).flatMap(role -> role.getPermissionsAssigned().stream())
                        .filter(Permission::getIsActive)
                        .map(Permission::getName)
                        .collect(Collectors.toSet()))
                .build();

        AuthResponse response = new AuthResponse(token, "Bearer", authUser);

        return BaseResponse.<AuthResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Login exitoso")
                .data(response)
                .build();
    }
}
