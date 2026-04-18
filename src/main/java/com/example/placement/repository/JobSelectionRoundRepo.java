package com.example.placement.repository;

import com.example.placement.entity.JobSelectionRound;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobSelectionRoundRepo extends JpaRepository<JobSelectionRound, Long> {

    List<JobSelectionRound> findByJob_IdOrderBySequenceOrderAsc(Long jobId);
}
