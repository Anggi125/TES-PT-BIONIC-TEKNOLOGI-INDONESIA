package com.example.demo.service;

import com.example.demo.dto.request.LoginRequestDto;
import com.example.demo.dto.request.UserRegisterRequestDto;
import com.example.demo.dto.response.LoginResponseDto;
import com.example.demo.dto.response.UserResponse;

public interface UserService {
    UserResponse registerUser(UserRegisterRequestDto request);
     LoginResponseDto login(LoginRequestDto request);
}
