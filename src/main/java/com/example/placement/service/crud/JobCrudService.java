package com.example.placement.service.crud;

import com.example.placement.common.entity.SelectionRound;
import com.example.placement.dto.job.JobCreateRequest;
import com.example.placement.dto.job.JobResponse;
import com.example.placement.dto.selection.JobSelectionRoundCreateRequest;
import com.example.placement.dto.selection.JobSelectionRoundUpdateRequest;
import com.example.placement.dto.job.JobUpdateRequest;
import com.example.placement.dto.selection.SelectionRoundResponse;
import com.example.placement.entity.main.CompanyProfile;
import com.example.placement.entity.main.JobProfile;
import com.example.placement.entity.main.StaffProfile;
import com.example.placement.common.enums.JobResultStatus;
import com.example.placement.common.enums.JobType;
import com.example.placement.repository.CompanyRepo;
import com.example.placement.repository.JobRepo;
import com.example.placement.repository.SelectionRoundRepo;
import com.example.placement.repository.StaffProfileRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class JobCrudService {

    private final JobRepo jobRepo;
    private final CompanyRepo companyRepo;
    private final StaffProfileRepo staffProfileRepo;
    private final SelectionRoundRepo selectionRoundRepo;

    public JobCrudService(
            JobRepo jobRepo,
            CompanyRepo companyRepo,
            StaffProfileRepo staffProfileRepo,
            SelectionRoundRepo selectionRoundRepo
    ) {
        this.jobRepo = jobRepo;
        this.companyRepo = companyRepo;
        this.staffProfileRepo = staffProfileRepo;
        this.selectionRoundRepo = selectionRoundRepo;
    }

    private void validateInternshipFieldsOnCreate(JobType jobType, String internshipDuration) {
        if (jobType == JobType.INTERNSHIP) {
            if (internshipDuration == null || internshipDuration.isBlank()) {
                throw new IllegalArgumentException("internshipDuration is required when jobType is INTERNSHIP");
            }
        } else if (internshipDuration != null && !internshipDuration.isBlank()) {
            throw new IllegalArgumentException("internshipDuration must only be set when jobType is INTERNSHIP");
        }
    }

    private void validateJobProfileRules(JobProfile job) {
        if (job.getPlacementCoordinator() == null) {
            throw new IllegalArgumentException("placementCoordinatorId is required");
        }
        if (job.getJobType() == JobType.INTERNSHIP) {
            if (job.getInternshipDuration() == null || job.getInternshipDuration().isBlank()) {
                throw new IllegalArgumentException("internshipDuration is required when jobType is INTERNSHIP");
            }
        } else if (job.getInternshipDuration() != null && !job.getInternshipDuration().isBlank()) {
            throw new IllegalArgumentException("internshipDuration must only be set when jobType is INTERNSHIP");
        }
    }

    private void normalizeInternshipDuration(JobProfile job) {
        if (job.getJobType() != JobType.INTERNSHIP) {
            job.setInternshipDuration(null);
        }
    }

    @Transactional
    public JobResponse create(JobCreateRequest req) {
        if (req.getCompanyId() == null || req.getJobType() == null || req.getWorkMode() == null) {
            throw new IllegalArgumentException("companyId, jobType, and workMode are required");
        }
        if (req.getPlacementCoordinatorId() == null) {
            throw new IllegalArgumentException("placementCoordinatorId is required");
        }
        validateInternshipFieldsOnCreate(req.getJobType(), req.getInternshipDuration());
        CompanyProfile company = companyRepo.findById(req.getCompanyId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));
        StaffProfile coordinator = staffProfileRepo.findById(req.getPlacementCoordinatorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff coordinator not found"));
        JobProfile job = new JobProfile();
        job.setCompany(company);
        job.setPlacementCoordinator(coordinator);
        job.setJobType(req.getJobType());
        job.setInternshipDuration(req.getInternshipDuration());
        job.setWorkMode(req.getWorkMode());
        job.setCtcLpa(req.getCtcLpa());
        job.setAdditionalInfo(req.getAdditionalInfo());
        job.setLastDateToApply(req.getLastDateToApply());
        job.setJobPostingTime(req.getJobPostingTime());
        job.setVenue(req.getVenue());
        job.setJobDescription(req.getJobDescription());
        job.setPreparationGuide(req.getPreparationGuide());
        job.setRequirements(req.getRequirements());
        job.setResponsibilities(req.getResponsibilities());
        job.setEligibility(req.getEligibility());
        job.setResultStatus(req.getResultStatus() != null ? req.getResultStatus() : JobResultStatus.NOT_ANNOUNCED);
        job.setResultDate(req.getResultDate());
        normalizeInternshipDuration(job);
        validateJobProfileRules(job);
        JobProfile saved = jobRepo.save(job);
        if (req.getSelectionRounds() != null) {
            for (JobSelectionRoundCreateRequest rc : req.getSelectionRounds()) {
                if (rc.getRoundName() == null || rc.getRoundName().isBlank() || rc.getSequenceOrder() == null) {
                    throw new IllegalArgumentException("Each round requires roundName and sequenceOrder");
                }
                SelectionRound jr = new SelectionRound();
                jr.setJob(saved);
                jr.setRoundName(rc.getRoundName().trim());
                jr.setSequenceNumber(rc.getSequenceOrder());
                jr.setScheduledDate(rc.getScheduledDate());
                saved.getSelectionRounds().add(jr);
            }
            saved = jobRepo.save(saved);
        }
        return PlacementDtoMapper.toJobResponse(saved);
    }

    @Transactional
    public JobResponse update(Long id, JobUpdateRequest req) {
        JobProfile job = jobRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));
        if (req.getCompanyId() != null) {
            CompanyProfile company = companyRepo.findById(req.getCompanyId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));
            job.setCompany(company);
        }
        if (req.getPlacementCoordinatorId() != null) {
            StaffProfile coordinator = staffProfileRepo.findById(req.getPlacementCoordinatorId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff coordinator not found"));
            job.setPlacementCoordinator(coordinator);
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
        if (req.getJobPostingTime() != null) {
            job.setJobPostingTime(req.getJobPostingTime());
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
                SelectionRound jr = new SelectionRound();
                jr.setJob(job);
                jr.setRoundName(ur.getRoundName().trim());
                jr.setSequenceNumber(ur.getSequenceOrder());
                jr.setScheduledDate(ur.getScheduledDate());
                job.getSelectionRounds().add(jr);
            }
        }
        normalizeInternshipDuration(job);
        validateJobProfileRules(job);
        return PlacementDtoMapper.toJobResponse(jobRepo.save(job));
    }

    @Transactional(readOnly = true)
    public JobResponse get(Long id) {
        JobProfile job = jobRepo.findById(id)
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

    @Transactional(readOnly = true)
    public List<SelectionRoundResponse> listSelectionRoundsForJob(Long jobId) {
        if (!jobRepo.existsById(jobId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found");
        }
        return selectionRoundRepo.findByJob_IdOrderBySequenceNumberAsc(jobId).stream()
                .map(PlacementDtoMapper::toSelectionRoundResponse)
                .toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!jobRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found");
        }
        jobRepo.deleteById(id);
    }
}
