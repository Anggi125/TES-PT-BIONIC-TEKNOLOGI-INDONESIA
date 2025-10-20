package com.example.demo.dto.response;

import lombok.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String email;
    private String status;
    private Set<String> roles;
    private ProfileResponse profile;
}
