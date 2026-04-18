package com.example.placement.service.crud;

import com.example.placement.dto.placement.DriveOfferedRoleCreateRequest;
import com.example.placement.dto.placement.DriveOfferedRoleResponse;
import com.example.placement.dto.placement.DriveOfferedRoleUpdateRequest;
import com.example.placement.entity.Drive;
import com.example.placement.entity.DriveOfferedRole;
import com.example.placement.repository.DriveOfferedRoleRepo;
import com.example.placement.repository.DriveRepo;
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

    public DriveOfferedRoleCrudService(DriveOfferedRoleRepo repo, DriveRepo driveRepo) {
        this.repo = repo;
        this.driveRepo = driveRepo;
    }

    @Transactional
    public DriveOfferedRoleResponse create(DriveOfferedRoleCreateRequest req) {
        if (req.getDriveId() == null || req.getRoleTitle() == null || req.getRoleTitle().isBlank()) {
            throw new IllegalArgumentException("driveId and roleTitle are required");
        }
        Drive drive = driveRepo.findById(req.getDriveId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Drive not found"));
        if (repo.countByDrive_Id(drive.getId()) >= MAX_ROLES_PER_DRIVE) {
            throw new IllegalArgumentException("A drive may have at most " + MAX_ROLES_PER_DRIVE + " offered roles");
        }
        DriveOfferedRole e = new DriveOfferedRole();
        e.setDrive(drive);
        e.setRoleTitle(req.getRoleTitle().trim());
        return PlacementDtoMapper.toDriveOfferedRoleResponse(repo.save(e));
    }

    @Transactional
    public DriveOfferedRoleResponse update(Long id, DriveOfferedRoleUpdateRequest req) {
        DriveOfferedRole e = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "DriveOfferedRole not found"));
        if (req.getRoleTitle() != null && !req.getRoleTitle().isBlank()) {
            e.setRoleTitle(req.getRoleTitle().trim());
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
