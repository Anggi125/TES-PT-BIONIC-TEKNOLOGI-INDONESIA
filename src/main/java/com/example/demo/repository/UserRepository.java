package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Cari user berdasarkan email (untuk login atau validasi)
    Optional<User> findByEmail(String email);

    // Cek apakah email sudah dipakai
    boolean existsByEmail(String email);
}
