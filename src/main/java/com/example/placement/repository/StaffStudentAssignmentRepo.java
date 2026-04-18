package com.example.placement.repository;

import com.example.placement.entity.StaffStudentAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffStudentAssignmentRepo extends JpaRepository<StaffStudentAssignment, Long> {
}
