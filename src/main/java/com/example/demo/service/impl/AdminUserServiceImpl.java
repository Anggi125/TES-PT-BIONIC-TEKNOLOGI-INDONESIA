package com.example.demo.service.impl;

import com.example.demo.dto.request.UpdateUserRequestDto;
import com.example.demo.dto.response.PagedResponse;
import com.example.demo.dto.response.ProfileResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AdminUserService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminUserServiceImpl implements AdminUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private UserResponse mapToResponse(User user) {
        ProfileResponse profileResponse = null;
        if (user.getProfile() != null) {
            profileResponse = ProfileResponse.builder()
                    .id(user.getProfile().getId())
                    .fullName(user.getProfile().getFullName())
                    .address(user.getProfile().getAddress())
                    .phone(user.getProfile().getPhone())
                    .build();
        }

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole().getName().name())
                .status(user.getStatus().name())
                .profile(profileResponse)
                .build();
    }

    // @Override
    // public List<UserResponse> getAllUsers() {
    //     return userRepository.findAll()
    //             .stream()
    //             .map(this::mapToResponse)
    //             .collect(Collectors.toList());
    // }

    @Override
public PagedResponse<UserResponse> getAllUsers(int page, int size, String sortBy, String direction) {
    Sort sort = direction.equalsIgnoreCase("desc")
            ? Sort.by(sortBy).descending()
            : Sort.by(sortBy).ascending();

    Pageable pageable = PageRequest.of(page, size, sort);

    Page<User> userPage = userRepository.findAll(pageable);

    List<UserResponse> users = userPage
            .getContent()
            .stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());

    return PagedResponse.<UserResponse>builder()
            .content(users)
            .currentPage(userPage.getNumber())
            .totalPages(userPage.getTotalPages())
            .totalElements(userPage.getTotalElements())
            .pageSize(userPage.getSize())
            .last(userPage.isLast())
            .build();
}

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return mapToResponse(user);
    }

    @Override
    public UserResponse updateUser(Long id, UpdateUserRequestDto request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (request.getEmail() != null) user.setEmail(request.getEmail());

        // Update role tunggal
        if (request.getRole() != null) {
            Role role = roleRepository.findByName(Role.RoleName.valueOf(request.getRole()))
                    .orElseThrow(() -> new BadRequestException("Role not found: " + request.getRole()));
            user.setRole(role);
        }

        // Update profile
        Profile profile = user.getProfile();
        if (profile == null) {
            profile = new Profile();
            profile.setUser(user);
        }
        if (request.getFullName() != null) profile.setFullName(request.getFullName());
        if (request.getPhone() != null) profile.setPhone(request.getPhone());
        if (request.getAddress() != null) profile.setAddress(request.getAddress());
        user.setProfile(profile);

        return mapToResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUserStatus(Long id, boolean active) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setStatus(active ? User.Status.ACTIVE : User.Status.INACTIVE);
        return mapToResponse(userRepository.save(user));
    }

@Override
public UserResponse deleteUser(Long id) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

    // simpan dulu data user untuk response
    UserResponse deletedUserResponse = mapToResponse(user);

    userRepository.delete(user);

    return deletedUserResponse;
}

}
