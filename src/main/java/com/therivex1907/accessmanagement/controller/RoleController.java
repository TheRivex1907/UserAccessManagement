package com.therivex1907.accessmanagement.controller;

import com.therivex1907.accessmanagement.dto.BaseResponse;
import com.therivex1907.accessmanagement.dto.RoleRequest;
import com.therivex1907.accessmanagement.dto.RoleResponse;
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
        try {
            BaseResponse<RoleResponse> response = roleService.getById(id);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponse<RoleResponse>> getByName(@RequestParam("name") String name) {
        try {
            BaseResponse<RoleResponse> response = roleService.getByName(name);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping()
    public ResponseEntity<BaseResponse<List<RoleResponse>>> getAll() {
        try {
            BaseResponse<List<RoleResponse>> response = roleService.getAll();
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse<RoleResponse>> createRole(@Valid @RequestBody RoleRequest roleRequest) {
        try {
            BaseResponse<RoleResponse> response = roleService.createRole(roleRequest);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<RoleResponse>> updateRole(@PathVariable Integer id, @Valid @RequestBody RoleRequest roleRequest) {
        try {
            BaseResponse<RoleResponse> response = roleService.updateRole(id, roleRequest);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteRole(@PathVariable Integer id) {
        try {
            BaseResponse<Void> response = roleService.deleteRole(id);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
