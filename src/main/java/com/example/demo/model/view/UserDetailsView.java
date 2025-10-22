package com.example.demo.model.view;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "v_user_details")
public class UserDetailsView {
    @Id
    private Long userId;
    private String email;
    private String status;
    private String roleName;
    private String fullName;
    private String address;
    private String phone;
}
