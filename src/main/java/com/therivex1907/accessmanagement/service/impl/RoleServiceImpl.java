package com.therivex1907.accessmanagement.service.impl;

import com.therivex1907.accessmanagement.repository.RoleRepository;
import com.therivex1907.accessmanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
}
