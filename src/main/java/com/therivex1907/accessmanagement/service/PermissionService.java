package com.therivex1907.accessmanagement.service;

import com.therivex1907.accessmanagement.dto.BaseResponse;
import com.therivex1907.accessmanagement.dto.permission.PermissionRequest;
import com.therivex1907.accessmanagement.dto.permission.PermissionResponse;

public interface PermissionService {
    BaseResponse<PermissionResponse> createPermission(PermissionRequest permissionRequest);
    BaseResponse<PermissionResponse> updatePermission(Integer id, PermissionRequest permissionRequest);
    BaseResponse<PermissionResponse> getById(Integer id);
    BaseResponse<Void> deletePermission(Integer id);
}
