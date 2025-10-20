package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    // Tambahan jika nanti mau ambil profil berdasarkan user_id
    Profile findByUserId(Long userId);
}
