package com.example.placement.repository;

import com.example.placement.entity.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BackLogRepo extends JpaRepository<Backlog, Long> {
    List<Backlog> findByEducationProfile_Id(Long educationProfileId);
}
