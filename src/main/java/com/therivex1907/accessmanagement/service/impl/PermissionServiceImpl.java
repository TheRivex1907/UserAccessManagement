package com.therivex1907.accessmanagement.service.impl;

import com.therivex1907.accessmanagement.repository.PermissionRepository;
import com.therivex1907.accessmanagement.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;
}
