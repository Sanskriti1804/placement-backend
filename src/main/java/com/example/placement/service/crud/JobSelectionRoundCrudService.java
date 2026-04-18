package com.example.placement.service.crud;

import com.example.placement.common.entity.SelectionRound;
import com.example.placement.dto.selection.JobSelectionRoundCreateRequest;
import com.example.placement.dto.selection.JobSelectionRoundUpdateRequest;
import com.example.placement.dto.selection.SelectionRoundResponse;
import com.example.placement.entity.main.JobProfile;
import com.example.placement.repository.JobRepo;
import com.example.placement.repository.SelectionRoundRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class JobSelectionRoundCrudService {

    private final SelectionRoundRepo roundRepo;
    private final JobRepo jobRepo;

    public JobSelectionRoundCrudService(SelectionRoundRepo roundRepo, JobRepo jobRepo) {
        this.roundRepo = roundRepo;
        this.jobRepo = jobRepo;
    }

    @Transactional
    public SelectionRoundResponse create(Long jobId, JobSelectionRoundCreateRequest req) {
        if (jobId == null) {
            throw new IllegalArgumentException("jobId is required");
        }
        if (req.getRoundName() == null || req.getRoundName().isBlank() || req.getSequenceOrder() == null) {
            throw new IllegalArgumentException("roundName and sequenceOrder are required");
        }
        JobProfile job = jobRepo.findById(jobId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));
        SelectionRound e = new SelectionRound();
        e.setJob(job);
        e.setRoundName(req.getRoundName().trim());
        e.setSequenceNumber(req.getSequenceOrder());
        e.setScheduledDate(req.getScheduledDate());
        return PlacementDtoMapper.toSelectionRoundResponse(roundRepo.save(e));
    }

    @Transactional
    public SelectionRoundResponse update(Long id, JobSelectionRoundUpdateRequest req) {
        SelectionRound e = roundRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Round not found"));
        if (e.getJob() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Round is not linked to a job");
        }
        if (req.getRoundName() != null && !req.getRoundName().isBlank()) {
            e.setRoundName(req.getRoundName().trim());
        }
        if (req.getSequenceOrder() != null) {
            e.setSequenceNumber(req.getSequenceOrder());
        }
        if (req.getScheduledDate() != null) {
            e.setScheduledDate(req.getScheduledDate());
        }
        return PlacementDtoMapper.toSelectionRoundResponse(roundRepo.save(e));
    }

    @Transactional(readOnly = true)
    public SelectionRoundResponse get(Long id) {
        return roundRepo.findById(id).map(PlacementDtoMapper::toSelectionRoundResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Round not found"));
    }

    @Transactional(readOnly = true)
    public List<SelectionRoundResponse> findAll() {
        return roundRepo.findAll().stream().map(PlacementDtoMapper::toSelectionRoundResponse).toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!roundRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Round not found");
        }
        roundRepo.deleteById(id);
    }
}
