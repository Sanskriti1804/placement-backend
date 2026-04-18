package com.example.placement.repository;

import com.example.placement.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EducationalProfileRepo extends JpaRepository<Education, Long> {
    Optional<Education> findByStudent_Id(Long studentId);
}
