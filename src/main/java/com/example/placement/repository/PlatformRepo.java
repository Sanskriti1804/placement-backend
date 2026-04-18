package com.example.placement.repository;

import com.example.placement.entity.Platform;
import com.example.placement.common.enums.PlatformType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlatformRepo extends JpaRepository<Platform, Long>{
    Optional<Platform> findByStudentIdAndType(Long studentId, PlatformType type);
    List<Platform> findByStudentId(Long studentId);
}
