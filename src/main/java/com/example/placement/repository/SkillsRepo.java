package com.example.placement.repository;

import com.example.placement.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillsRepo extends JpaRepository<Skill, Long> {
    Optional<Skill> findByName(String name);
}
