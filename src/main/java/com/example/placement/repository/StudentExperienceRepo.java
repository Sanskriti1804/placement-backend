package com.example.placement.repository;

import com.example.placement.entity.StudentExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentExperienceRepo extends JpaRepository<StudentExperience, Long> {

    List<StudentExperience> findByStudent_Id(Long studentId);
}
