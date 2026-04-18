package com.example.placement.repository;

import com.example.placement.entity.BranchType;
import com.example.placement.entity.DriveBranch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DriveBranchRepo extends JpaRepository<DriveBranch, Long> {

    List<DriveBranch> findByDrive_Id(Long driveId);

    Optional<DriveBranch> findByDrive_IdAndBranch(Long driveId, BranchType branch);
}
