package com.example.demo.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserRequestDto {
    private String email;
    private String fullName;
    private String phone;
    private String address;
    private String role; // role tunggal, bukan Set<String>
}
