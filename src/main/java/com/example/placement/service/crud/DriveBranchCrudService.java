package com.example.placement.service.crud;

import com.example.placement.dto.drive.DriveBranchCreateRequest;
import com.example.placement.dto.drive.DriveBranchResponse;
import com.example.placement.dto.drive.DriveBranchUpdateRequest;
import com.example.placement.entity.main.DriveProfile;
import com.example.placement.entity.DriveBranch;
import com.example.placement.repository.DriveBranchRepo;
import com.example.placement.repository.DriveRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DriveBranchCrudService {

    private final DriveBranchRepo repo;
    private final DriveRepo driveRepo;

    public DriveBranchCrudService(DriveBranchRepo repo, DriveRepo driveRepo) {
        this.repo = repo;
        this.driveRepo = driveRepo;
    }

    @Transactional
    public DriveBranchResponse create(DriveBranchCreateRequest req) {
        if (req.getDriveId() == null || req.getBranch() == null) {
            throw new IllegalArgumentException("driveId and branch are required");
        }
        DriveProfile drive = driveRepo.findById(req.getDriveId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Drive not found"));
        DriveBranch e = new DriveBranch();
        e.setDrive(drive);
        e.setBranch(req.getBranch());
        return PlacementDtoMapper.toDriveBranchResponse(repo.save(e));
    }

    @Transactional
    public DriveBranchResponse update(Long id, DriveBranchUpdateRequest req) {
        DriveBranch e = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "DriveBranch not found"));
        if (req.getBranch() != null) {
            e.setBranch(req.getBranch());
        }
        return PlacementDtoMapper.toDriveBranchResponse(repo.save(e));
    }

    @Transactional(readOnly = true)
    public DriveBranchResponse get(Long id) {
        return repo.findById(id).map(PlacementDtoMapper::toDriveBranchResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "DriveBranch not found"));
    }

    @Transactional(readOnly = true)
    public List<DriveBranchResponse> findAll() {
        return repo.findAll().stream().map(PlacementDtoMapper::toDriveBranchResponse).toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "DriveBranch not found");
        }
        repo.deleteById(id);
    }
}
