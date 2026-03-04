package com.therivex1907.accessmanagement.service.impl;

import com.therivex1907.accessmanagement.dto.BaseResponse;
import com.therivex1907.accessmanagement.dto.UserRequest;
import com.therivex1907.accessmanagement.dto.UserResponse;
import com.therivex1907.accessmanagement.dto.UserSearchFilter;
import com.therivex1907.accessmanagement.entity.Role;
import com.therivex1907.accessmanagement.entity.User;
import com.therivex1907.accessmanagement.repository.UserRepository;
import com.therivex1907.accessmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public BaseResponse<UserResponse> createUser(UserRequest userRequest) {
        return null;
    }

    @Override
    public BaseResponse<List<UserResponse>> getAllUsers() {
        List<User> users = userRepository.findByIsActiveTrue();
        List<UserResponse> usersModified = users.stream().map(this::mapToResponse).toList();
        return BaseResponse.<List<UserResponse>>builder()
                .status(HttpStatus.OK.value())
                .message("Ok")
                .data(usersModified)
                .build();
    }

    @Override
    public BaseResponse<UserResponse> getById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontró el usuario"));
        UserResponse userModified = mapToResponse(user);
        return BaseResponse.<UserResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Usuario encontrado")
                .data(userModified)
                .build();
    }

    @Override
    public BaseResponse<List<UserResponse>> searchUsers(UserSearchFilter filter) {
        List<User> users = userRepository.searchUser(filter.getLastName(), filter.getEmail());
        if (users.isEmpty())
            throw new RuntimeException("No se encontró la informacion deseada");
        List<UserResponse> usersModified = users.stream().map(this::mapToResponse).toList();
        return BaseResponse.<List<UserResponse>>builder()
                .status(HttpStatus.OK.value())
                .message("Información encontrada")
                .data(usersModified)
                .build();
    }

//    @Override
//    public BaseResponse<UserResponse> getByEmail(String email) {
//        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("No existe el usuario con ese correo"));
//        UserResponse userModified = mapToResponse(user);
//        return BaseResponse.<UserResponse>builder()
//                .status(HttpStatus.OK.value())
//                .message("Usuario encontrado")
//                .data(userModified)
//                .build();
//    }

    @Override
    public BaseResponse<Void> deleteById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontró el usuario"));
        if (!user.getIsActive()) {
            throw new RuntimeException("El usuario ya se encuentra eliminado");
        }
        user.setIsActive(false);
        userRepository.save(user);
        return BaseResponse.<Void>builder()
                .status(HttpStatus.NO_CONTENT.value())
                .message("Usuario eliminado")
                .data(null)
                .build();
    }

    private UserResponse mapToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhoneNumber(user.getEmail());
        userResponse.setIsActive(user.getIsActive());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdateAt(user.getUpdateAt());
        userResponse.setRolesId(user.getRolesAssigned().stream().map(Role::getId).collect(Collectors.toSet()));
        return userResponse;
    }
}
