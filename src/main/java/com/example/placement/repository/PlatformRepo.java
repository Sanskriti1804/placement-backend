package com.example.placement.repository;

import com.example.placement.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlatformRepo extends JpaRepository<Platform, Long>{ }
