package com.example.demo.service;

import com.example.demo.dto.response.ProfileResponse;

public interface ProfileService {
    ProfileResponse getMyProfile(String token);
    ProfileResponse updateMyProfile(String token, ProfileResponse request);
    void deleteMyProfile(String token);
}
