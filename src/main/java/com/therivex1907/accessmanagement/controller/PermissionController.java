package com.therivex1907.accessmanagement.controller;

import com.therivex1907.accessmanagement.dto.BaseResponse;
import com.therivex1907.accessmanagement.dto.PageResponse;
import com.therivex1907.accessmanagement.dto.permission.PermissionCreateRequest;
import com.therivex1907.accessmanagement.dto.permission.PermissionResponse;
import com.therivex1907.accessmanagement.dto.permission.PermissionUpdateRequest;
import com.therivex1907.accessmanagement.service.PermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @PreAuthorize("hasAuthority('PERMISSION_LIST')")
    @GetMapping
    public ResponseEntity<BaseResponse<PageResponse<PermissionResponse>>> findAll(Pageable pageable) {
        BaseResponse<PageResponse<PermissionResponse>> response = permissionService.getAllPermissions(pageable);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasAuthority('PERMISSION_READ')")
    @GetMapping("{id}")
    public ResponseEntity<BaseResponse<PermissionResponse>> getById(@PathVariable Integer id) {
        BaseResponse<PermissionResponse> response = permissionService.getById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasAuthority('PERMISSION_CREATE')")
    @PostMapping
    public ResponseEntity<BaseResponse<PermissionResponse>> createPermission(@Valid @RequestBody PermissionCreateRequest permissionRequest) {
        BaseResponse<PermissionResponse> response = permissionService.createPermission(permissionRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasAuthority('PERMISSION_UPDATE')")
    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse<PermissionResponse>> updatePermission(@PathVariable Integer id, @Valid @RequestBody PermissionUpdateRequest permissionRequest) {
        BaseResponse<PermissionResponse> response = permissionService.updatePermission(id, permissionRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasAuthority('PERMISSION_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deletePermission(@PathVariable Integer id) {
        BaseResponse<Void> response = permissionService.deletePermission(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
