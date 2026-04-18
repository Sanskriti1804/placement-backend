package com.example.placement.service.crud;

import com.example.placement.dto.placement.JobSelectionRoundCreateRequest;
import com.example.placement.dto.placement.JobSelectionRoundResponse;
import com.example.placement.dto.placement.JobSelectionRoundUpdateRequest;
import com.example.placement.entity.main.JobProfile;
import com.example.placement.entity.JobSelectionRound;
import com.example.placement.entity.RoundCompletionStatus;
import com.example.placement.repository.JobRepo;
import com.example.placement.repository.JobSelectionRoundRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class JobSelectionRoundCrudService {

    private final JobSelectionRoundRepo roundRepo;
    private final JobRepo jobRepo;

    public JobSelectionRoundCrudService(JobSelectionRoundRepo roundRepo, JobRepo jobRepo) {
        this.roundRepo = roundRepo;
        this.jobRepo = jobRepo;
    }

    @Transactional
    public JobSelectionRoundResponse create(Long jobId, JobSelectionRoundCreateRequest req) {
        if (jobId == null) {
            throw new IllegalArgumentException("jobId is required");
        }
        if (req.getRoundName() == null || req.getRoundName().isBlank() || req.getSequenceOrder() == null) {
            throw new IllegalArgumentException("roundName and sequenceOrder are required");
        }
        JobProfile job = jobRepo.findById(jobId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));
        JobSelectionRound e = new JobSelectionRound();
        e.setJob(job);
        e.setRoundName(req.getRoundName().trim());
        e.setSequenceOrder(req.getSequenceOrder());
        e.setScheduledDate(req.getScheduledDate());
        e.setCompletionStatus(req.getCompletionStatus() != null ? req.getCompletionStatus() : RoundCompletionStatus.PENDING);
        return PlacementDtoMapper.toRoundResponse(roundRepo.save(e));
    }

    @Transactional
    public JobSelectionRoundResponse update(Long id, JobSelectionRoundUpdateRequest req) {
        JobSelectionRound e = roundRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Round not found"));
        if (req.getRoundName() != null && !req.getRoundName().isBlank()) {
            e.setRoundName(req.getRoundName().trim());
        }
        if (req.getSequenceOrder() != null) {
            e.setSequenceOrder(req.getSequenceOrder());
        }
        if (req.getScheduledDate() != null) {
            e.setScheduledDate(req.getScheduledDate());
        }
        if (req.getCompletionStatus() != null) {
            e.setCompletionStatus(req.getCompletionStatus());
        }
        return PlacementDtoMapper.toRoundResponse(roundRepo.save(e));
    }

    @Transactional(readOnly = true)
    public JobSelectionRoundResponse get(Long id) {
        return roundRepo.findById(id).map(PlacementDtoMapper::toRoundResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Round not found"));
    }

    @Transactional(readOnly = true)
    public List<JobSelectionRoundResponse> findAll() {
        return roundRepo.findAll().stream().map(PlacementDtoMapper::toRoundResponse).toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!roundRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Round not found");
        }
        roundRepo.deleteById(id);
    }
}
