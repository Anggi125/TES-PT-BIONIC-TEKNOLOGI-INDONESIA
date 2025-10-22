package com.example.demo.controller;

import com.example.demo.dto.request.UpdateUserRequestDto;
import com.example.demo.dto.response.PagedResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;

    // @GetMapping("/all")
    // public ResponseEntity<List<UserResponse>> getAllUsers() {
    //     return ResponseEntity.ok(adminUserService.getAllUsers());
    // }
    @GetMapping("/all")
public ResponseEntity<PagedResponse<UserResponse>> getAllUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String direction
) {
    return ResponseEntity.ok(adminUserService.getAllUsers(page, size, sortBy, direction));
}

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(adminUserService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequestDto request
    ) {
        return ResponseEntity.ok(adminUserService.updateUser(id, request));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<UserResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam("active") boolean active
    ) {
        return ResponseEntity.ok(adminUserService.updateUserStatus(id, active));
    }

      @DeleteMapping("/{id}")
public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Long id) {
    UserResponse deletedUser = adminUserService.deleteUser(id);

    Map<String, Object> response = new HashMap<>();
    response.put("message", "User berhasil dihapus");
    response.put("data", deletedUser);

    return ResponseEntity.ok(response);
}

}
