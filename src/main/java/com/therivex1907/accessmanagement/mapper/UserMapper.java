package com.therivex1907.accessmanagement.mapper;

import com.therivex1907.accessmanagement.dto.user.UserCreateRequest;
import com.therivex1907.accessmanagement.dto.user.UserUpdateRequest;
import com.therivex1907.accessmanagement.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "rolesAssigned", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User createFromDto(UserCreateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "rolesAssigned", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    void updateUserFromDto(UserUpdateRequest request, @MappingTarget User user);
}
