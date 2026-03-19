package com.therivex1907.accessmanagement.service.impl;

import com.therivex1907.accessmanagement.entity.User;
import com.therivex1907.accessmanagement.repository.UserRepository;
import com.therivex1907.accessmanagement.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        user.getRolesAssigned().forEach(role -> role.getPermissionsAssigned().size());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRolesAssigned().stream()
                        .flatMap(role -> role.getPermissionsAssigned().stream())
                        .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                        .toList()
        );
    }
}
