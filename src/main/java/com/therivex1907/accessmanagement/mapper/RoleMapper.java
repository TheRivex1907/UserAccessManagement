package com.therivex1907.accessmanagement.mapper;

import com.therivex1907.accessmanagement.dto.role.RoleUpdateRequest;
import com.therivex1907.accessmanagement.entity.Role;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "permissionsAssigned", ignore = true)
    void updateRoleFromDto(RoleUpdateRequest request, @MappingTarget Role role);
}
