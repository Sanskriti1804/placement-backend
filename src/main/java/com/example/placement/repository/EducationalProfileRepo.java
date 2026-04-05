package com.example.placement.repository;

import com.example.placement.entity.EducationProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EducationalProfileRepo extends JpaRepository<EducationProfile, Long> {
    Optional<EducationProfile> findByStudent_Id(Long studentId);
}
