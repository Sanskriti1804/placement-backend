package com.example.placement.service.crud;

import com.example.placement.dto.drive.DriveOfferedRoleCreateRequest;
import com.example.placement.dto.drive.DriveOfferedRoleResponse;
import com.example.placement.dto.drive.DriveOfferedRoleUpdateRequest;
import com.example.placement.entity.DriveOfferedRole;
import com.example.placement.entity.main.DriveProfile;
import com.example.placement.entity.main.JobProfile;
import com.example.placement.repository.DriveOfferedRoleRepo;
import com.example.placement.repository.DriveRepo;
import com.example.placement.repository.JobRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DriveOfferedRoleCrudService {

    private static final int MAX_ROLES_PER_DRIVE = 4;

    private final DriveOfferedRoleRepo repo;
    private final DriveRepo driveRepo;
    private final JobRepo jobRepo;

    public DriveOfferedRoleCrudService(DriveOfferedRoleRepo repo, DriveRepo driveRepo, JobRepo jobRepo) {
        this.repo = repo;
        this.driveRepo = driveRepo;
        this.jobRepo = jobRepo;
    }

    @Transactional
    public DriveOfferedRoleResponse create(DriveOfferedRoleCreateRequest req) {
        if (req.getDriveId() == null || req.getRoleName() == null || req.getRoleName().isBlank()) {
            throw new IllegalArgumentException("driveId and roleName are required");
        }
        DriveProfile drive = driveRepo.findById(req.getDriveId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Drive not found"));
        if (repo.countByDrive_Id(drive.getId()) >= MAX_ROLES_PER_DRIVE) {
            throw new IllegalArgumentException("A drive may have at most " + MAX_ROLES_PER_DRIVE + " offered roles");
        }
        DriveOfferedRole e = new DriveOfferedRole();
        e.setDrive(drive);
        e.setRoleName(req.getRoleName().trim());
        if (req.getLinkedJobId() != null) {
            JobProfile job = jobRepo.findById(req.getLinkedJobId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));
            e.setLinkedJob(job);
        }
        return PlacementDtoMapper.toDriveOfferedRoleResponse(repo.save(e));
    }

    @Transactional
    public DriveOfferedRoleResponse update(Long id, DriveOfferedRoleUpdateRequest req) {
        DriveOfferedRole e = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "DriveOfferedRole not found"));
        if (req.getRoleName() != null && !req.getRoleName().isBlank()) {
            e.setRoleName(req.getRoleName().trim());
        }
        if (req.getLinkedJobId() != null) {
            JobProfile job = jobRepo.findById(req.getLinkedJobId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));
            e.setLinkedJob(job);
        }
        return PlacementDtoMapper.toDriveOfferedRoleResponse(repo.save(e));
    }

    @Transactional(readOnly = true)
    public DriveOfferedRoleResponse get(Long id) {
        return repo.findById(id).map(PlacementDtoMapper::toDriveOfferedRoleResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "DriveOfferedRole not found"));
    }

    @Transactional(readOnly = true)
    public List<DriveOfferedRoleResponse> findAll() {
        return repo.findAll().stream().map(PlacementDtoMapper::toDriveOfferedRoleResponse).toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "DriveOfferedRole not found");
        }
        repo.deleteById(id);
    }
}
