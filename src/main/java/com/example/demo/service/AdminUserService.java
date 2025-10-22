package com.example.demo.service;

import com.example.demo.dto.request.UpdateUserRequestDto;
import com.example.demo.dto.response.PagedResponse;
import com.example.demo.dto.response.UserResponse;

import java.util.List;

public interface AdminUserService {
    // List<UserResponse> getAllUsers();
    PagedResponse<UserResponse> getAllUsers(int page, int size, String sortBy, String direction);
    UserResponse getUserById(Long id);
    UserResponse updateUser(Long id, UpdateUserRequestDto request);
    UserResponse updateUserStatus(Long id, boolean active);
    UserResponse deleteUser(Long id);
}
