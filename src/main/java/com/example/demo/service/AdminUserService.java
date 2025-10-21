package com.example.demo.service;

import com.example.demo.dto.request.UpdateUserRequestDto;
import com.example.demo.dto.response.UserResponse;

import java.util.List;

public interface AdminUserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    UserResponse updateUser(Long id, UpdateUserRequestDto request);
    UserResponse updateUserStatus(Long id, boolean active);
    void deleteUser(Long id);
}
