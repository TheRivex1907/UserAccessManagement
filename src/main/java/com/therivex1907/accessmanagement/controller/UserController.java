package com.therivex1907.accessmanagement.controller;

import com.therivex1907.accessmanagement.dto.BaseResponse;
import com.therivex1907.accessmanagement.dto.user.UserCreateRequest;
import com.therivex1907.accessmanagement.dto.user.UserResponse;
import com.therivex1907.accessmanagement.dto.user.UserSearchFilter;
import com.therivex1907.accessmanagement.dto.user.UserUpdateRequest;
import com.therivex1907.accessmanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<BaseResponse<UserResponse>> createUser(@Valid @RequestBody UserCreateRequest user) {
        BaseResponse<UserResponse> response = userService.createUser(user);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse<UserResponse>> updateUser(@PathVariable Integer id, @Valid @RequestBody UserUpdateRequest user) {
        BaseResponse<UserResponse> response = userService.updateUser(id, user);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<UserResponse>> getUser(@PathVariable Integer id) {
        BaseResponse<UserResponse> response = userService.getById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<UserResponse>>> getAllUsers() {
        BaseResponse<List<UserResponse>> response = userService.getAllUsers();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/search")
    public ResponseEntity<BaseResponse<List<UserResponse>>> getUsersFilter(@RequestBody UserSearchFilter filter) {
        BaseResponse<List<UserResponse>> response = userService.searchUsers(filter);
        return  ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteUser(@PathVariable Integer id) {
        BaseResponse<Void> response = userService.deleteById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
