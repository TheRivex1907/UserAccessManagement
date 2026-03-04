package com.therivex1907.accessmanagement.service.impl;

import com.therivex1907.accessmanagement.dto.BaseResponse;
import com.therivex1907.accessmanagement.dto.UserRequest;
import com.therivex1907.accessmanagement.dto.UserResponse;
import com.therivex1907.accessmanagement.dto.UserSearchFilter;
import com.therivex1907.accessmanagement.entity.Role;
import com.therivex1907.accessmanagement.entity.User;
import com.therivex1907.accessmanagement.repository.RoleRepository;
import com.therivex1907.accessmanagement.repository.UserRepository;
import com.therivex1907.accessmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public BaseResponse<UserResponse> createUser(UserRequest userRequest) {
        Optional<User> user = userRepository.findByEmail(userRequest.getEmail());
        if (user.isPresent()) {
            throw new RuntimeException("Ya existe un usuario con ese correo");
        }

        Set<Role> roles = new HashSet<>(roleRepository.findAllById(userRequest.getRolesId()));
        if (roles.size() != userRequest.getRolesId().size()) {
            throw new RuntimeException("Uno o más roles no existen");
        }
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        User newUser = new User();
        newUser.setFirstName(userRequest.getFirstName());
        newUser.setLastName(userRequest.getLastName());
        newUser.setEmail(userRequest.getEmail());
        newUser.setPassword(encodedPassword);
        newUser.setPhoneNumber(userRequest.getPhoneNumber());
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setRolesAssigned(roles);
        newUser.setIsActive(true);
        userRepository.save(newUser);

        UserResponse userModified = mapToResponse(newUser);
        return BaseResponse.<UserResponse>builder()
                .status(HttpStatus.CREATED.value())
                .message("Usuario creado")
                .data(userModified)
                .build();
    }

    @Transactional
    @Override
    public BaseResponse<UserResponse> updateUser(Integer id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe aquel usuario"));
        Optional<User> userExistEmail = userRepository.findByEmail(userRequest.getEmail());
        if (userExistEmail.isPresent() && !userExistEmail.get().getId().equals(id)) {
            throw new RuntimeException("Ya existe un usuario con ese correo");
        }
        Set<Role> newRoles = new HashSet<>(roleRepository.findAllById(userRequest.getRolesId()));
        if (newRoles.size() != userRequest.getRolesId().size()) {
            throw new RuntimeException("Uno o más roles no existen");
        }
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        if (userRequest.getPassword() != null && !userRequest.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setRolesAssigned(newRoles);
        user.setUpdateAt(LocalDateTime.now());
        userRepository.save(user);

        UserResponse userModified = mapToResponse(user);
        return BaseResponse.<UserResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Usuario modificado")
                .data(userModified)
                .build();
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
                .status(HttpStatus.OK.value())
                .message("Usuario eliminado")
                .data(null)
                .build();
    }

    private UserResponse mapToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getFirstName() + " " + user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setIsActive(user.getIsActive());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdateAt(user.getUpdateAt());
        userResponse.setRolesId(user.getRolesAssigned().stream().map(Role::getId).collect(Collectors.toSet()));
        return userResponse;
    }
}