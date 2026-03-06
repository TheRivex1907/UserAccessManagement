package com.therivex1907.accessmanagement.service;

import com.therivex1907.accessmanagement.dto.BaseResponse;
import com.therivex1907.accessmanagement.dto.permission.PermissionCreateRequest;
import com.therivex1907.accessmanagement.dto.permission.PermissionResponse;
import com.therivex1907.accessmanagement.dto.permission.PermissionUpdateRequest;

import java.util.List;

public interface PermissionService {
    BaseResponse<PermissionResponse> createPermission(PermissionCreateRequest permissionRequest);
    BaseResponse<PermissionResponse> updatePermission(Integer id, PermissionUpdateRequest permissionRequest);
    BaseResponse<PermissionResponse> getById(Integer id);
    BaseResponse<Void> deletePermission(Integer id);
    BaseResponse<List<PermissionResponse>> getAllPermissions();
}
