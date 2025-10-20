package com.example.demo.repository;

import com.example.demo.model.Role;
import com.example.demo.model.Role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    // Cari role berdasarkan nama enum
    Optional<Role> findByName(RoleName name);
}
