package com.therivex1907.accessmanagement.controller;

import com.therivex1907.accessmanagement.dto.BaseResponse;
import com.therivex1907.accessmanagement.dto.permission.PermissionCreateRequest;
import com.therivex1907.accessmanagement.dto.permission.PermissionResponse;
import com.therivex1907.accessmanagement.dto.permission.PermissionUpdateRequest;
import com.therivex1907.accessmanagement.service.PermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<PermissionResponse>>> findAll() {
        BaseResponse<List<PermissionResponse>> response = permissionService.getAllPermissions();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse<PermissionResponse>> getById(@PathVariable Integer id) {
        BaseResponse<PermissionResponse> response = permissionService.getById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<BaseResponse<PermissionResponse>> createPermission(@Valid @RequestBody PermissionCreateRequest permissionRequest) {
        BaseResponse<PermissionResponse> response = permissionService.createPermission(permissionRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse<PermissionResponse>> updatePermission(@PathVariable Integer id, @Valid @RequestBody PermissionUpdateRequest permissionRequest) {
        BaseResponse<PermissionResponse> response = permissionService.updatePermission(id, permissionRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deletePermission(@PathVariable Integer id) {
        BaseResponse<Void> response = permissionService.deletePermission(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
