package com.example.placement.repository;

import com.example.placement.entity.DriveSelectionRound;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriveSelectionRoundRepo extends JpaRepository<DriveSelectionRound, Long> {

    List<DriveSelectionRound> findByDrive_IdOrderBySequenceOrderAsc(Long driveId);
}
