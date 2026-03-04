package com.therivex1907.accessmanagement.service;

import com.therivex1907.accessmanagement.dto.BaseResponse;
import com.therivex1907.accessmanagement.dto.UserRequest;
import com.therivex1907.accessmanagement.dto.UserResponse;
import com.therivex1907.accessmanagement.dto.UserSearchFilter;

import java.util.List;

public interface UserService {
    BaseResponse<UserResponse> createUser(UserRequest userRequest);
    BaseResponse<UserResponse> updateUser(Integer id, UserRequest userRequest);
    BaseResponse<List<UserResponse>> getAllUsers();
    BaseResponse<UserResponse> getById(Integer id);
//    BaseResponse<UserResponse> getByEmail(String email);
//    BaseResponse<UserResponse> getByLastName(String lastName);
    BaseResponse<List<UserResponse>> searchUsers(UserSearchFilter userSearchFilter);
    BaseResponse<Void> deleteById(Integer id);
}
