package com.example.placement.repository;

import com.example.placement.entity.StaffDepartmentAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffDepartmentAssignmentRepo extends JpaRepository<StaffDepartmentAssignment, Long> {
}
