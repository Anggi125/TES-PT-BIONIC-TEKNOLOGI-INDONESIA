package com.example.demo.service.impl;

import com.example.demo.config.JwtUtil;
import com.example.demo.dto.request.LoginRequestDto;
import com.example.demo.dto.request.UserRegisterRequestDto;
import com.example.demo.dto.response.LoginResponseDto;
import com.example.demo.dto.response.ProfileResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.model.Profile;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public UserResponse registerUser(UserRegisterRequestDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email sudah digunakan.");
        }

        Role defaultRole = roleRepository.findByName(Role.RoleName.CUSTOMER)
                .orElseThrow(() -> new RuntimeException("Role CUSTOMER tidak ditemukan."));

        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword()) // nanti bisa diganti ke BCrypt
                .status(User.Status.ACTIVE)
                .role(defaultRole)
                .build();

        Profile profile = Profile.builder()
                .fullName(request.getFullName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .user(user)
                .build();

        user.setProfile(profile);
        User savedUser = userRepository.save(user);

        return convertToResponse(savedUser);
    }

    private UserResponse convertToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole().getName().name())
                .status(user.getStatus().name())
                .profile(ProfileResponse.builder()
                        .id(user.getProfile().getId())
                        .fullName(user.getProfile().getFullName())
                        .address(user.getProfile().getAddress())
                        .phone(user.getProfile().getPhone())
                        .build())
                .build();
    }

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email tidak ditemukan."));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Password salah.");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new LoginResponseDto(token, "Bearer");
    }
}
