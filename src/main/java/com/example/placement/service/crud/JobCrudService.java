package com.example.placement.service.crud;

import com.example.placement.dto.placement.JobCreateRequest;
import com.example.placement.dto.placement.JobResponse;
import com.example.placement.dto.placement.JobSelectionRoundCreateRequest;
import com.example.placement.dto.placement.JobSelectionRoundUpdateRequest;
import com.example.placement.dto.placement.JobUpdateRequest;
import com.example.placement.entity.Company;
import com.example.placement.entity.Job;
import com.example.placement.entity.JobResultStatus;
import com.example.placement.entity.JobSelectionRound;
import com.example.placement.entity.PlacementCoordinator;
import com.example.placement.repository.CompanyRepo;
import com.example.placement.repository.JobRepo;
import com.example.placement.repository.PlacementCoordinatorRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class JobCrudService {

    private final JobRepo jobRepo;
    private final CompanyRepo companyRepo;
    private final PlacementCoordinatorRepo coordinatorRepo;

    public JobCrudService(JobRepo jobRepo, CompanyRepo companyRepo, PlacementCoordinatorRepo coordinatorRepo) {
        this.jobRepo = jobRepo;
        this.companyRepo = companyRepo;
        this.coordinatorRepo = coordinatorRepo;
    }

    @Transactional
    public JobResponse create(JobCreateRequest req) {
        if (req.getCompanyId() == null || req.getJobType() == null || req.getWorkMode() == null) {
            throw new IllegalArgumentException("companyId, jobType, and workMode are required");
        }
        Company company = companyRepo.findById(req.getCompanyId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));
        Job job = new Job();
        job.setCompany(company);
        if (req.getPlacementCoordinatorId() != null) {
            PlacementCoordinator pc = coordinatorRepo.findById(req.getPlacementCoordinatorId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Coordinator not found"));
            job.setPlacementCoordinator(pc);
        }
        job.setJobType(req.getJobType());
        job.setInternshipDuration(req.getInternshipDuration());
        job.setWorkMode(req.getWorkMode());
        job.setCtcLpa(req.getCtcLpa());
        job.setAdditionalInfo(req.getAdditionalInfo());
        job.setLastDateToApply(req.getLastDateToApply());
        job.setVenue(req.getVenue());
        job.setJobDescription(req.getJobDescription());
        job.setPreparationGuide(req.getPreparationGuide());
        job.setRequirements(req.getRequirements());
        job.setResponsibilities(req.getResponsibilities());
        job.setEligibility(req.getEligibility());
        job.setResultStatus(req.getResultStatus() != null ? req.getResultStatus() : JobResultStatus.NOT_ANNOUNCED);
        job.setResultDate(req.getResultDate());
        Job saved = jobRepo.save(job);
        if (req.getSelectionRounds() != null) {
            for (JobSelectionRoundCreateRequest rc : req.getSelectionRounds()) {
                if (rc.getRoundName() == null || rc.getRoundName().isBlank() || rc.getSequenceOrder() == null) {
                    throw new IllegalArgumentException("Each round requires roundName and sequenceOrder");
                }
                JobSelectionRound jr = new JobSelectionRound();
                jr.setJob(saved);
                jr.setRoundName(rc.getRoundName().trim());
                jr.setSequenceOrder(rc.getSequenceOrder());
                jr.setScheduledDate(rc.getScheduledDate());
                saved.getSelectionRounds().add(jr);
            }
            saved = jobRepo.save(saved);
        }
        return PlacementDtoMapper.toJobResponse(saved);
    }

    @Transactional
    public JobResponse update(Long id, JobUpdateRequest req) {
        Job job = jobRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));
        if (req.getCompanyId() != null) {
            Company company = companyRepo.findById(req.getCompanyId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));
            job.setCompany(company);
        }
        if (req.getPlacementCoordinatorId() != null) {
            PlacementCoordinator pc = coordinatorRepo.findById(req.getPlacementCoordinatorId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Coordinator not found"));
            job.setPlacementCoordinator(pc);
        }
        if (req.getJobType() != null) {
            job.setJobType(req.getJobType());
        }
        if (req.getInternshipDuration() != null) {
            job.setInternshipDuration(req.getInternshipDuration());
        }
        if (req.getWorkMode() != null) {
            job.setWorkMode(req.getWorkMode());
        }
        if (req.getCtcLpa() != null) {
            job.setCtcLpa(req.getCtcLpa());
        }
        if (req.getAdditionalInfo() != null) {
            job.setAdditionalInfo(req.getAdditionalInfo());
        }
        if (req.getLastDateToApply() != null) {
            job.setLastDateToApply(req.getLastDateToApply());
        }
        if (req.getVenue() != null) {
            job.setVenue(req.getVenue());
        }
        if (req.getJobDescription() != null) {
            job.setJobDescription(req.getJobDescription());
        }
        if (req.getPreparationGuide() != null) {
            job.setPreparationGuide(req.getPreparationGuide());
        }
        if (req.getRequirements() != null) {
            job.setRequirements(req.getRequirements());
        }
        if (req.getResponsibilities() != null) {
            job.setResponsibilities(req.getResponsibilities());
        }
        if (req.getEligibility() != null) {
            job.setEligibility(req.getEligibility());
        }
        if (req.getResultStatus() != null) {
            job.setResultStatus(req.getResultStatus());
        }
        if (req.getResultDate() != null) {
            job.setResultDate(req.getResultDate());
        }
        if (req.getSelectionRounds() != null) {
            job.getSelectionRounds().clear();
            for (JobSelectionRoundUpdateRequest ur : req.getSelectionRounds()) {
                if (ur.getRoundName() == null || ur.getRoundName().isBlank() || ur.getSequenceOrder() == null) {
                    throw new IllegalArgumentException("Each round requires roundName and sequenceOrder");
                }
                JobSelectionRound jr = new JobSelectionRound();
                jr.setJob(job);
                jr.setRoundName(ur.getRoundName().trim());
                jr.setSequenceOrder(ur.getSequenceOrder());
                jr.setScheduledDate(ur.getScheduledDate());
                job.getSelectionRounds().add(jr);
            }
        }
        return PlacementDtoMapper.toJobResponse(jobRepo.save(job));
    }

    @Transactional(readOnly = true)
    public JobResponse get(Long id) {
        Job job = jobRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));
        job.getSelectionRounds().size();
        return PlacementDtoMapper.toJobResponse(job);
    }

    @Transactional(readOnly = true)
    public List<JobResponse> findAll() {
        return jobRepo.findAll().stream().map(j -> {
            j.getSelectionRounds().size();
            return PlacementDtoMapper.toJobResponse(j);
        }).toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!jobRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found");
        }
        jobRepo.deleteById(id);
    }
}
