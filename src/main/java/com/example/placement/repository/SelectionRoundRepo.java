package com.example.placement.repository;

import com.example.placement.common.entity.SelectionRound;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SelectionRoundRepo extends JpaRepository<SelectionRound, Long> {

    List<SelectionRound> findByJob_IdOrderBySequenceNumberAsc(Long jobId);

    List<SelectionRound> findByDrive_IdOrderBySequenceNumberAsc(Long driveId);
}
