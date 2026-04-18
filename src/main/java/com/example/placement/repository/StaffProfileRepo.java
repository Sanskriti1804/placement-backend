package com.example.placement.repository;

import com.example.placement.entity.main.StaffProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffProfileRepo extends JpaRepository<StaffProfile, Long> {

    Optional<StaffProfile> findByUser_Id(Long userId);
}
