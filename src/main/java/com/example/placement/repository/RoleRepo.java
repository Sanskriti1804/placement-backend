package com.example.placement.repository;

import com.example.placement.entity.Role;
import com.example.placement.common.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long>{
    Optional<Role> findByRoleName(RoleType roleName);
}
