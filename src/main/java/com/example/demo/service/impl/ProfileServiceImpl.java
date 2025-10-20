package com.example.demo.service.impl;

import com.example.demo.config.JwtUtil;
import com.example.demo.dto.response.ProfileResponse;
import com.example.demo.model.Profile;
import com.example.demo.model.User;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProfileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public ProfileResponse getMyProfile(String token) {
        // 🔧 Perbaikan di sini: aman walau token ada prefix "Bearer "
        String cleanToken = token != null && token.startsWith("Bearer ")
                ? token.substring(7)
                : token;
        String email = jwtUtil.extractUsername(cleanToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan."));
        Profile profile = user.getProfile();

        return ProfileResponse.builder()
                .id(profile.getId())
                .fullName(profile.getFullName())
                .address(profile.getAddress())
                .phone(profile.getPhone())
                .build();
    }

    @Override
    @Transactional
    public ProfileResponse updateMyProfile(String token, ProfileResponse request) {
        // 🔧 Perbaikan sama di sini
        String cleanToken = token != null && token.startsWith("Bearer ")
                ? token.substring(7)
                : token;
        String email = jwtUtil.extractUsername(cleanToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan."));
        Profile profile = user.getProfile();

        profile.setFullName(request.getFullName());
        profile.setAddress(request.getAddress());
        profile.setPhone(request.getPhone());

        profileRepository.save(profile);

        return ProfileResponse.builder()
                .id(profile.getId())
                .fullName(profile.getFullName())
                .address(profile.getAddress())
                .phone(profile.getPhone())
                .build();
    }

    @Override
    @Transactional
    public void deleteMyProfile(String token) {
        // 🔧 Perbaikan juga di sini
        String cleanToken = token != null && token.startsWith("Bearer ")
                ? token.substring(7)
                : token;
        String email = jwtUtil.extractUsername(cleanToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan."));
        Profile profile = user.getProfile();

        profileRepository.delete(profile);
        userRepository.delete(user);
    }
}
