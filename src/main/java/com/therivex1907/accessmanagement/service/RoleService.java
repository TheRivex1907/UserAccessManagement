package com.therivex1907.accessmanagement.service;

import com.therivex1907.accessmanagement.dto.BaseResponse;
import com.therivex1907.accessmanagement.dto.PageResponse;
import com.therivex1907.accessmanagement.dto.role.RoleCreateRequest;
import com.therivex1907.accessmanagement.dto.role.RoleResponse;
import com.therivex1907.accessmanagement.dto.role.RoleUpdateRequest;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface RoleService {
    BaseResponse<RoleResponse> createRole(RoleCreateRequest roleRequest);
    BaseResponse<RoleResponse> updateRole(Integer id, RoleUpdateRequest roleRequest);
    BaseResponse<PageResponse<RoleResponse>> getAll(Pageable pageable);
    BaseResponse<RoleResponse> getById(Integer id);
    BaseResponse<PageResponse<RoleResponse>> getByName(String name, Pageable pageable);
    BaseResponse<Void> deleteRole(Integer id);
}
