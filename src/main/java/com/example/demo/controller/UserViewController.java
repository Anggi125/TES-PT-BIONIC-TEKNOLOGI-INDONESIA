package com.example.demo.controller;

import com.example.demo.dto.response.UserViewResponse;
import com.example.demo.service.UserViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserViewController {

    private final UserViewService userViewService;

    @GetMapping("/view")
    public ResponseEntity<List<UserViewResponse>> getAllFromView() {
        return ResponseEntity.ok(userViewService.getAllUserViews());
    }
}
