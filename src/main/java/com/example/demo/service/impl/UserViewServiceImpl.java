package com.example.demo.service.impl;

import com.example.demo.dto.response.UserViewResponse;
import com.example.demo.repository.view.UserDetailsViewRepository;
import com.example.demo.service.UserViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserViewServiceImpl implements UserViewService {

    private final UserDetailsViewRepository viewRepository;

    @Override
    public List<UserViewResponse> getAllUserViews() {
        return viewRepository.findAll()
                .stream()
                .map(v -> UserViewResponse.builder()
                        .userId(v.getUserId())
                        .email(v.getEmail())
                        .status(v.getStatus())
                        .roleName(v.getRoleName())
                        .fullName(v.getFullName())
                        .address(v.getAddress())
                        .phone(v.getPhone())
                        .build())
                .collect(Collectors.toList());
    }
}
