package com.example.placement.service.crud;

import com.example.placement.dto.placement.StaffDriveAssignmentCreateRequest;
import com.example.placement.dto.placement.StaffDriveAssignmentResponse;
import com.example.placement.dto.placement.StaffDriveAssignmentUpdateRequest;
import com.example.placement.entity.main.DriveProfile;
import com.example.placement.entity.StaffDriveAssignment;
import com.example.placement.entity.main.StaffProfile;
import com.example.placement.repository.DriveRepo;
import com.example.placement.repository.StaffDriveAssignmentRepo;
import com.example.placement.repository.StaffProfileRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StaffDriveAssignmentCrudService {

    private final StaffDriveAssignmentRepo repo;
    private final StaffProfileRepo staffProfileRepo;
    private final DriveRepo driveRepo;

    public StaffDriveAssignmentCrudService(
            StaffDriveAssignmentRepo repo,
            StaffProfileRepo staffProfileRepo,
            DriveRepo driveRepo
    ) {
        this.repo = repo;
        this.staffProfileRepo = staffProfileRepo;
        this.driveRepo = driveRepo;
    }

    @Transactional
    public StaffDriveAssignmentResponse create(StaffDriveAssignmentCreateRequest req) {
        if (req.getStaffProfileId() == null || req.getDriveId() == null) {
            throw new IllegalArgumentException("staffProfileId and driveId are required");
        }
        StaffProfile staff = staffProfileRepo.findById(req.getStaffProfileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff profile not found"));
        DriveProfile drive = driveRepo.findById(req.getDriveId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Drive not found"));
        StaffDriveAssignment e = new StaffDriveAssignment();
        e.setStaff(staff);
        e.setDrive(drive);
        return PlacementDtoMapper.toStaffDriveAssignmentResponse(repo.save(e));
    }

    @Transactional
    public StaffDriveAssignmentResponse update(Long id, StaffDriveAssignmentUpdateRequest req) {
        StaffDriveAssignment e = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignment not found"));
        if (req.getDriveId() != null) {
            DriveProfile drive = driveRepo.findById(req.getDriveId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Drive not found"));
            e.setDrive(drive);
        }
        return PlacementDtoMapper.toStaffDriveAssignmentResponse(repo.save(e));
    }

    @Transactional(readOnly = true)
    public StaffDriveAssignmentResponse get(Long id) {
        return repo.findById(id).map(PlacementDtoMapper::toStaffDriveAssignmentResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignment not found"));
    }

    @Transactional(readOnly = true)
    public List<StaffDriveAssignmentResponse> findAll() {
        return repo.findAll().stream().map(PlacementDtoMapper::toStaffDriveAssignmentResponse).toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignment not found");
        }
        repo.deleteById(id);
    }
}
