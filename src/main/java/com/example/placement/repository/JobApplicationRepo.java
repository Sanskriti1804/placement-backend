package com.example.placement.repository;

import com.example.placement.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobApplicationRepo extends JpaRepository<JobApplication, Long> {

    Optional<JobApplication> findByStudent_IdAndJob_Id(Long studentId, Long jobId);

    List<JobApplication> findByStudent_Id(Long studentId);

    List<JobApplication> findByJob_Id(Long jobId);

    List<JobApplication> findByCompany_Id(Long companyId);
}
