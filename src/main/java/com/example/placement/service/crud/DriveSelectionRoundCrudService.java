package com.example.placement.service.crud;

import com.example.placement.common.entity.SelectionRound;
import com.example.placement.dto.selection.JobSelectionRoundCreateRequest;
import com.example.placement.dto.selection.JobSelectionRoundUpdateRequest;
import com.example.placement.dto.selection.SelectionRoundResponse;
import com.example.placement.entity.main.DriveProfile;
import com.example.placement.repository.DriveRepo;
import com.example.placement.repository.SelectionRoundRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DriveSelectionRoundCrudService {

    private final SelectionRoundRepo roundRepo;
    private final DriveRepo driveRepo;

    public DriveSelectionRoundCrudService(SelectionRoundRepo roundRepo, DriveRepo driveRepo) {
        this.roundRepo = roundRepo;
        this.driveRepo = driveRepo;
    }

    @Transactional
    public SelectionRoundResponse create(Long driveId, JobSelectionRoundCreateRequest req) {
        if (driveId == null) {
            throw new IllegalArgumentException("driveId is required");
        }
        if (req.getRoundName() == null || req.getRoundName().isBlank() || req.getSequenceOrder() == null) {
            throw new IllegalArgumentException("roundName and sequenceOrder are required");
        }
        DriveProfile drive = driveRepo.findById(driveId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Drive not found"));
        SelectionRound e = new SelectionRound();
        e.setDrive(drive);
        e.setRoundName(req.getRoundName().trim());
        e.setSequenceNumber(req.getSequenceOrder());
        e.setScheduledDate(req.getScheduledDate());
        return PlacementDtoMapper.toSelectionRoundResponse(roundRepo.save(e));
    }

    @Transactional
    public SelectionRoundResponse update(Long id, JobSelectionRoundUpdateRequest req) {
        SelectionRound e = roundRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Round not found"));
        if (e.getDrive() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Round is not linked to a drive");
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
