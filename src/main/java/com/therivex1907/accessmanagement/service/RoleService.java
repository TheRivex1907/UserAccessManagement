package com.therivex1907.accessmanagement.service;

import com.therivex1907.accessmanagement.dto.BaseResponse;
import com.therivex1907.accessmanagement.dto.RoleRequest;
import com.therivex1907.accessmanagement.dto.RoleResponse;

public interface RoleService {
    BaseResponse<RoleResponse> createRole(RoleRequest roleRequest);
    BaseResponse<RoleResponse> updateRole(Integer id, RoleRequest roleRequest);
    BaseResponse<RoleResponse> getById(Integer id);
    BaseResponse<RoleResponse> getByName(String name);
    BaseResponse<Void> deleteRole(Integer id);
}
