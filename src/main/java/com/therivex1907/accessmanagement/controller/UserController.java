package com.therivex1907.accessmanagement.controller;

import com.therivex1907.accessmanagement.dto.BaseResponse;
import com.therivex1907.accessmanagement.dto.PageResponse;
import com.therivex1907.accessmanagement.dto.user.UserCreateRequest;
import com.therivex1907.accessmanagement.dto.user.UserResponse;
import com.therivex1907.accessmanagement.dto.user.UserSearchFilter;
import com.therivex1907.accessmanagement.dto.user.UserUpdateRequest;
import com.therivex1907.accessmanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('USER_CREATE')")
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

    @PreAuthorize("hasAuthority('USER_READ')")
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<UserResponse>> getUser(@PathVariable Integer id) {
        BaseResponse<UserResponse> response = userService.getById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasAuthority('USER_LIST')")
    @GetMapping
    public ResponseEntity<BaseResponse<PageResponse<UserResponse>>> getAllUsers(Pageable pageable) {
        BaseResponse<PageResponse<UserResponse>> response = userService.getAllUsers(pageable);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasAuthority('USER_FILTER')")
    @PostMapping("/search")
    public ResponseEntity<BaseResponse<PageResponse<UserResponse>>> getUsersFilter(@RequestBody UserSearchFilter filter, Pageable pageable) {
        BaseResponse<PageResponse<UserResponse>> response = userService.searchUsers(filter, pageable);
        return  ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasAuthority('USER_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteUser(@PathVariable Integer id) {
        BaseResponse<Void> response = userService.deleteById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
