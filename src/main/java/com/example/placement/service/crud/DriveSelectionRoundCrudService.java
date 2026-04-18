package com.example.placement.service.crud;

import com.example.placement.dto.placement.DriveSelectionRoundResponse;
import com.example.placement.dto.placement.JobSelectionRoundCreateRequest;
import com.example.placement.dto.placement.JobSelectionRoundUpdateRequest;
import com.example.placement.entity.Drive;
import com.example.placement.entity.DriveSelectionRound;
import com.example.placement.entity.RoundCompletionStatus;
import com.example.placement.repository.DriveRepo;
import com.example.placement.repository.DriveSelectionRoundRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DriveSelectionRoundCrudService {

    private final DriveSelectionRoundRepo roundRepo;
    private final DriveRepo driveRepo;

    public DriveSelectionRoundCrudService(DriveSelectionRoundRepo roundRepo, DriveRepo driveRepo) {
        this.roundRepo = roundRepo;
        this.driveRepo = driveRepo;
    }

    @Transactional
    public DriveSelectionRoundResponse create(Long driveId, JobSelectionRoundCreateRequest req) {
        if (driveId == null) {
            throw new IllegalArgumentException("driveId is required");
        }
        if (req.getRoundName() == null || req.getRoundName().isBlank() || req.getSequenceOrder() == null) {
            throw new IllegalArgumentException("roundName and sequenceOrder are required");
        }
        Drive drive = driveRepo.findById(driveId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Drive not found"));
        DriveSelectionRound e = new DriveSelectionRound();
        e.setDrive(drive);
        e.setRoundName(req.getRoundName().trim());
        e.setSequenceOrder(req.getSequenceOrder());
        e.setScheduledDate(req.getScheduledDate());
        e.setCompletionStatus(req.getCompletionStatus() != null ? req.getCompletionStatus() : RoundCompletionStatus.PENDING);
        return PlacementDtoMapper.toDriveSelectionRoundResponse(roundRepo.save(e));
    }

    @Transactional
    public DriveSelectionRoundResponse update(Long id, JobSelectionRoundUpdateRequest req) {
        DriveSelectionRound e = roundRepo.findById(id)
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
        return PlacementDtoMapper.toDriveSelectionRoundResponse(roundRepo.save(e));
    }

    @Transactional(readOnly = true)
    public DriveSelectionRoundResponse get(Long id) {
        return roundRepo.findById(id).map(PlacementDtoMapper::toDriveSelectionRoundResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Round not found"));
    }

    @Transactional(readOnly = true)
    public List<DriveSelectionRoundResponse> findAll() {
        return roundRepo.findAll().stream().map(PlacementDtoMapper::toDriveSelectionRoundResponse).toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!roundRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Round not found");
        }
        roundRepo.deleteById(id);
    }
}
