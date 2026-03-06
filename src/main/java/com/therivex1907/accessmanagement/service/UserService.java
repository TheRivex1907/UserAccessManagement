package com.therivex1907.accessmanagement.service;

import com.therivex1907.accessmanagement.dto.BaseResponse;
import com.therivex1907.accessmanagement.dto.user.UserCreateRequest;
import com.therivex1907.accessmanagement.dto.user.UserResponse;
import com.therivex1907.accessmanagement.dto.user.UserSearchFilter;
import com.therivex1907.accessmanagement.dto.user.UserUpdateRequest;

import java.util.List;

public interface UserService {
    BaseResponse<UserResponse> createUser(UserCreateRequest userRequest);
    BaseResponse<UserResponse> updateUser(Integer id, UserUpdateRequest userRequest);
    BaseResponse<List<UserResponse>> getAllUsers();
    BaseResponse<UserResponse> getById(Integer id);
//    BaseResponse<UserResponse> getByEmail(String email);
//    BaseResponse<UserResponse> getByLastName(String lastName);
    BaseResponse<List<UserResponse>> searchUsers(UserSearchFilter userSearchFilter);
    BaseResponse<Void> deleteById(Integer id);
}
