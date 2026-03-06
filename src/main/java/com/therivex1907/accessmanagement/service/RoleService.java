package com.therivex1907.accessmanagement.service;

import com.therivex1907.accessmanagement.dto.BaseResponse;
import com.therivex1907.accessmanagement.dto.role.RoleCreateRequest;
import com.therivex1907.accessmanagement.dto.role.RoleResponse;
import com.therivex1907.accessmanagement.dto.role.RoleUpdateRequest;

import java.util.List;

public interface RoleService {
    BaseResponse<RoleResponse> createRole(RoleCreateRequest roleRequest);
    BaseResponse<RoleResponse> updateRole(Integer id, RoleUpdateRequest roleRequest);
    BaseResponse<List<RoleResponse>> getAll();
    BaseResponse<RoleResponse> getById(Integer id);
    BaseResponse<RoleResponse> getByName(String name);
    BaseResponse<Void> deleteRole(Integer id);
}
