package com.example.placement.repository;

import com.example.placement.entity.main.CompanyProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<CompanyProfile, Long> {
}
