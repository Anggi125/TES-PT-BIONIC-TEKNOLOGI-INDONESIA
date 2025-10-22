package com.example.demo.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserViewResponse {
    private Long userId;
    private String email;
    private String status;
    private String roleName;
    private String fullName;
    private String address;
    private String phone;
}
