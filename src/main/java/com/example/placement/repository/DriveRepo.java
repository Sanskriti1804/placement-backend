package com.example.placement.repository;

import com.example.placement.entity.main.DriveProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriveRepo extends JpaRepository<DriveProfile, Long> {
}
