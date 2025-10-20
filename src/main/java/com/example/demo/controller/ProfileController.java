package com.example.demo.controller;

import com.example.demo.dto.response.ProfileResponse;
import com.example.demo.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/getme")
    public ResponseEntity<ProfileResponse> getMyProfile(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(profileService.getMyProfile(token));
    }

    @PutMapping("/edit")
    public ResponseEntity<ProfileResponse> updateMyProfile(
            @RequestHeader("Authorization") String token,
            @RequestBody ProfileResponse request
    ) {
        return ResponseEntity.ok(profileService.updateMyProfile(token, request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteMyProfile(@RequestHeader("Authorization") String token) {
        profileService.deleteMyProfile(token);
        return ResponseEntity.ok("Profile kamu berhasil dihapus.");
    }
}
