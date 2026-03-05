package com.therivex1907.accessmanagement.service.impl;

import com.therivex1907.accessmanagement.dto.BaseResponse;
import com.therivex1907.accessmanagement.dto.PermissionRequest;
import com.therivex1907.accessmanagement.dto.PermissionResponse;
import com.therivex1907.accessmanagement.entity.Permission;
import com.therivex1907.accessmanagement.repository.PermissionRepository;
import com.therivex1907.accessmanagement.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Transactional
    @Override
    public BaseResponse<PermissionResponse> createPermission(PermissionRequest permissionRequest) {
        Optional<Permission> permission = permissionRepository.findByNameIgnoreCase(permissionRequest.getName());
        if (permission.isPresent()) {
            throw new RuntimeException("Ya existe un permiso con ese nombre");
        }
        Permission newPermission = new Permission();
        newPermission.setName(permissionRequest.getName());
        newPermission.setIsActive(true);
        newPermission.setCreatedAt(LocalDateTime.now());
        permissionRepository.save(newPermission);

        PermissionResponse permissionModified = mapToResponse(newPermission);
        return BaseResponse.<PermissionResponse>builder()
                .status(HttpStatus.CREATED.value())
                .message("Permiso creado")
                .data(permissionModified)
                .build();
    }

    @Transactional
    @Override
    public BaseResponse<PermissionResponse> updatePermission(Integer id, PermissionRequest permissionRequest) {
        Permission permission = permissionRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe el permiso especificado"));
        Optional<Permission> permissionExist = permissionRepository.findByNameIgnoreCase(permissionRequest.getName());
        if (permissionExist.isPresent() && !permissionExist.get().getId().equals(id)) {
            throw new RuntimeException("Ya existe un permiso con ese nombre");
        }
        permission.setName(permissionRequest.getName());
        permission.setIsActive(permissionRequest.getIsActive());
        permission.setUpdatedAt(LocalDateTime.now());
        permissionRepository.save(permission);
        PermissionResponse permissionModified = mapToResponse(permission);

        return BaseResponse.<PermissionResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Permiso modificado")
                .data(permissionModified)
                .build();
    }

    @Override
    public BaseResponse<PermissionResponse> getById(Integer id) {
        Permission permission = permissionRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el permiso con ese id"));
        PermissionResponse permissionModified = mapToResponse(permission);

        return BaseResponse.<PermissionResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Permiso encontrado")
                .data(permissionModified)
                .build();
    }

    @Override
    public BaseResponse<Void> deletePermission(Integer id) {
        Permission permission = permissionRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe el permiso con ese id"));
        permission.setIsActive(false);
        permissionRepository.save(permission);

        return BaseResponse.<Void>builder()
                .status(HttpStatus.OK.value())
                .message("Permiso eliminado")
                .data(null)
                .build();
    }

    private PermissionResponse mapToResponse(Permission permission) {
        PermissionResponse permissionResponse = new PermissionResponse();
        permissionResponse.setId(permission.getId());
        permissionResponse.setName(permission.getName());
        permissionResponse.setCreatedAt(permission.getCreatedAt());
        permissionResponse.setUpdatedAt(permission.getUpdatedAt());
        permissionResponse.setIsActive(permission.getIsActive());
        return permissionResponse;
    }
}
