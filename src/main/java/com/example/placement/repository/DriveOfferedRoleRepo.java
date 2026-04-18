package com.example.placement.repository;

import com.example.placement.entity.DriveOfferedRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriveOfferedRoleRepo extends JpaRepository<DriveOfferedRole, Long> {

    List<DriveOfferedRole> findByDrive_Id(Long driveId);

    long countByDrive_Id(Long driveId);
}
