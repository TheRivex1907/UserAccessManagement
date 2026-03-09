package com.therivex1907.accessmanagement.controller;

import com.therivex1907.accessmanagement.dto.BaseResponse;
import com.therivex1907.accessmanagement.dto.role.RoleCreateRequest;
import com.therivex1907.accessmanagement.dto.role.RoleResponse;
import com.therivex1907.accessmanagement.dto.role.RoleUpdateRequest;
import com.therivex1907.accessmanagement.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<RoleResponse>> getById(@PathVariable Integer id) {
        BaseResponse<RoleResponse> response = roleService.getById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponse<RoleResponse>> getByName(@RequestParam("name") String name) {
        BaseResponse<RoleResponse> response = roleService.getByName(name);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping()
    public ResponseEntity<BaseResponse<List<RoleResponse>>> getAll() {
        BaseResponse<List<RoleResponse>> response = roleService.getAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<BaseResponse<RoleResponse>> createRole(@Valid @RequestBody RoleCreateRequest roleRequest) {
        BaseResponse<RoleResponse> response = roleService.createRole(roleRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse<RoleResponse>> updateRole(@PathVariable Integer id, @Valid @RequestBody RoleUpdateRequest roleRequest) {
        BaseResponse<RoleResponse> response = roleService.updateRole(id, roleRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteRole(@PathVariable Integer id) {
        BaseResponse<Void> response = roleService.deleteRole(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
