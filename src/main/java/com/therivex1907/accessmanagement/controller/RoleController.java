package com.therivex1907.accessmanagement.controller;

import com.therivex1907.accessmanagement.dto.BaseResponse;
import com.therivex1907.accessmanagement.dto.PageResponse;
import com.therivex1907.accessmanagement.dto.role.RoleCreateRequest;
import com.therivex1907.accessmanagement.dto.role.RoleResponse;
import com.therivex1907.accessmanagement.dto.role.RoleUpdateRequest;
import com.therivex1907.accessmanagement.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/v1/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAuthority('ROLE_READ')")
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<RoleResponse>> getById(@PathVariable Integer id) {
        BaseResponse<RoleResponse> response = roleService.getById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasAuthority('ROLE_FILTER')")
    @GetMapping("/search")
    public ResponseEntity<BaseResponse<PageResponse<RoleResponse>>> getByName(@RequestParam("name") String name, Pageable pageable) {
        BaseResponse<PageResponse<RoleResponse>> response = roleService.getByName(name, pageable);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasAuthority('ROLE_LIST')")
    @GetMapping
    public ResponseEntity<BaseResponse<PageResponse<RoleResponse>>> getAll(Pageable pageable) {
        BaseResponse<PageResponse<RoleResponse>> response = roleService.getAll(pageable);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasAuthority('ROLE_CREATE')")
    @PostMapping
    public ResponseEntity<BaseResponse<RoleResponse>> createRole(@Valid @RequestBody RoleCreateRequest roleRequest) {
        BaseResponse<RoleResponse> response = roleService.createRole(roleRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasAuthority('ROLE_UPDATE')")
    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse<RoleResponse>> updateRole(@PathVariable Integer id, @Valid @RequestBody RoleUpdateRequest roleRequest) {
        BaseResponse<RoleResponse> response = roleService.updateRole(id, roleRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasAuthority('ROLE_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteRole(@PathVariable Integer id) {
        BaseResponse<Void> response = roleService.deleteRole(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
