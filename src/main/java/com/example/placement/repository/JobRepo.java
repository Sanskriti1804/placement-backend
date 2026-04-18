package com.example.placement.repository;

import com.example.placement.entity.main.JobProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepo extends JpaRepository<JobProfile, Long> {
}
