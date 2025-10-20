package com.example.demo.dto.request;

import lombok.*;
import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterRequestDto {

    // Data user
    @Email(message = "Email tidak valid")
    @NotBlank(message = "Email tidak boleh kosong")
    private String email;

    @NotBlank(message = "Password tidak boleh kosong")
    @Size(min = 6, message = "Password minimal 6 karakter")
    private String password;

    // Data profil
    @NotBlank(message = "Nama lengkap wajib diisi")
    private String fullName;

    private String address;
    private String phone;
}
