package com.therivex1907.accessmanagement.service.impl;

import com.therivex1907.accessmanagement.dto.BaseResponse;
import com.therivex1907.accessmanagement.dto.PageResponse;
import com.therivex1907.accessmanagement.dto.role.RoleCreateRequest;
import com.therivex1907.accessmanagement.dto.role.RoleResponse;
import com.therivex1907.accessmanagement.dto.role.RoleUpdateRequest;
import com.therivex1907.accessmanagement.entity.Permission;
import com.therivex1907.accessmanagement.entity.Role;
import com.therivex1907.accessmanagement.exception.BadRequestException;
import com.therivex1907.accessmanagement.exception.DuplicateResourceException;
import com.therivex1907.accessmanagement.exception.ResourceNotFoundException;
import com.therivex1907.accessmanagement.mapper.RoleMapper;
import com.therivex1907.accessmanagement.repository.PermissionRepository;
import com.therivex1907.accessmanagement.repository.RoleRepository;
import com.therivex1907.accessmanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RoleMapper roleMapper;

    @Transactional
    @Override
    public BaseResponse<RoleResponse> createRole(RoleCreateRequest roleRequest) {
        Optional<Role> roleExist = roleRepository.findByNameIgnoreCase(roleRequest.getName());
        if (roleExist.isPresent()) {
            throw new DuplicateResourceException("Ya existe un rol con ese nombre");
        }
        Role newRole = roleMapper.createFromDto(roleRequest);
        if (roleRequest.getPermissionsId() != null && !roleRequest.getPermissionsId().isEmpty()) {
            Set<Permission> permissions = new HashSet<>(permissionRepository.findAllById(roleRequest.getPermissionsId()));
            if (permissions.size() != roleRequest.getPermissionsId().size()) {
                throw new ResourceNotFoundException("Uno o más permisos no existen");
            }
            newRole.setPermissionsAssigned(permissions);
        } else {
            newRole.setPermissionsAssigned(new HashSet<>());
        }
        roleRepository.save(newRole);

        RoleResponse roleModified = mapToResponse(newRole);
        return BaseResponse.<RoleResponse>builder()
                .status(HttpStatus.CREATED.value())
                .message("Role creado")
                .data(roleModified)
                .build();
    }

    @Transactional
    @Override
    public BaseResponse<RoleResponse> updateRole(Integer id, RoleUpdateRequest roleRequest) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe un rol con ese id"));
        if (roleRequest.getName() != null) {
            Optional<Role> roleExist = roleRepository.findByNameIgnoreCase(roleRequest.getName());
            if (roleExist.isPresent() && !roleExist.get().getId().equals(id)) {
                throw new DuplicateResourceException("Ya existe un rol con ese nombre");
            }
            role.setName(roleRequest.getName());
        }
        if (roleRequest.getPermissionsId() != null) {
            Set<Permission> permissions = new HashSet<>(permissionRepository.findAllById(roleRequest.getPermissionsId()));
            if (permissions.size() != roleRequest.getPermissionsId().size()) {
                throw new ResourceNotFoundException("Uno o mas permisos no existen");
            }
            role.setPermissionsAssigned(permissions);
        }
        roleMapper.updateRoleFromDto(roleRequest, role);
        roleRepository.saveAndFlush(role);
        RoleResponse roleModified = mapToResponse(role);
        return BaseResponse.<RoleResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Rol modificado")
                .data(roleModified)
                .build();
    }

    @Override
    public BaseResponse<PageResponse<RoleResponse>> getAll(Pageable pageable) {
        Page<Role> roles = roleRepository.findAll(pageable);
        PageResponse<RoleResponse> rolesModified = new PageResponse<>(
                roles.getContent().stream().map(this::mapToResponse).toList(),
                roles.getNumber(),
                roles.getSize(),
                (int) roles.getTotalElements(),
                roles.getTotalPages()
        );
        return BaseResponse.<PageResponse<RoleResponse>>builder()
                .status(HttpStatus.OK.value())
                .message("Ok")
                .data(rolesModified)
                .build();
    }

    @Override
    public BaseResponse<RoleResponse> getById(Integer id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe el rol con ese id"));
        RoleResponse roleModified = mapToResponse(role);
        return BaseResponse.<RoleResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Rol encontrado")
                .data(roleModified)
                .build();
    }

    @Override
    public BaseResponse<PageResponse<RoleResponse>> getByName(String name, Pageable pageable) {
        Page<Role> roles = roleRepository.findByNameContainingIgnoreCase(name, pageable);
        PageResponse<RoleResponse> rolesModified = new PageResponse<>(
                roles.getContent().stream().map(this::mapToResponse).toList(),
                roles.getNumber(),
                roles.getSize(),
                (int) roles.getTotalElements(),
                roles.getTotalPages()
        );
        return BaseResponse.<PageResponse<RoleResponse>>builder()
                .status(HttpStatus.OK.value())
                .message("Roles encontrados")
                .data(rolesModified)
                .build();
    }

    @Transactional
    @Override
    public BaseResponse<Void> deleteRole(Integer id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe aquel rol"));
        if (!role.getIsActive()) {
            throw new BadRequestException("El rol ya esta eliminado");
        }
        role.setIsActive(false);
        roleRepository.save(role);
        return BaseResponse.<Void>builder()
                .status(HttpStatus.OK.value())
                .message("Rol eliminado")
                .data(null)
                .build();
    }

    private RoleResponse mapToResponse(Role role) {
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setId(role.getId());
        roleResponse.setName(role.getName());
        roleResponse.setCreatedAt(role.getCreatedAt());
        roleResponse.setUpdateAt(role.getUpdatedAt());
        roleResponse.setIsActive(role.getIsActive());
        roleResponse.setPermissions(role.getPermissionsAssigned().stream().map(Permission::getId).collect(Collectors.toSet()));
        return roleResponse;
    }
}
