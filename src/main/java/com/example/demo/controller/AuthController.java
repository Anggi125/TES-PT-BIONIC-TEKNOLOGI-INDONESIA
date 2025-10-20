package com.example.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.request.LoginRequestDto;
import com.example.demo.dto.request.UserRegisterRequestDto;
import com.example.demo.dto.response.LoginResponseDto;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.service.UserService;
import com.example.demo.config.JwtUtil;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRegisterRequestDto request) {
        UserResponse response = userService.registerUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(userService.login(request));
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok("Logout berhasil. Token dinonaktifkan di sisi client.");
    }

  
    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refreshToken(@RequestHeader("Authorization") String token) {
        String oldToken = token.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(oldToken);
        String newToken = jwtUtil.generateToken(username);
        return ResponseEntity.ok(new LoginResponseDto(newToken, "Bearer"));
    }
}
