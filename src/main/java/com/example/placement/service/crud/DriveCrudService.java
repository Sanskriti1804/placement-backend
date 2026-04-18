package com.example.placement.service.crud;

import com.example.placement.dto.placement.DriveCreateRequest;
import com.example.placement.dto.placement.DriveResponse;
import com.example.placement.dto.placement.DriveUpdateRequest;
import com.example.placement.entity.BranchType;
import com.example.placement.entity.Company;
import com.example.placement.entity.Drive;
import com.example.placement.entity.DriveBranch;
import com.example.placement.entity.DriveOfferedRole;
import com.example.placement.entity.PlacementCoordinator;
import com.example.placement.repository.CompanyRepo;
import com.example.placement.repository.DriveRepo;
import com.example.placement.repository.PlacementCoordinatorRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DriveCrudService {

    private final DriveRepo driveRepo;
    private final CompanyRepo companyRepo;
    private final PlacementCoordinatorRepo coordinatorRepo;

    public DriveCrudService(DriveRepo driveRepo, CompanyRepo companyRepo, PlacementCoordinatorRepo coordinatorRepo) {
        this.driveRepo = driveRepo;
        this.companyRepo = companyRepo;
        this.coordinatorRepo = coordinatorRepo;
    }

    @Transactional
    public DriveResponse create(DriveCreateRequest req) {
        if (req.getDriveName() == null || req.getDriveName().isBlank()
                || req.getCompanyId() == null || req.getRegistrationDeadline() == null) {
            throw new IllegalArgumentException("driveName, companyId, and registrationDeadline are required");
        }
        Company company = companyRepo.findById(req.getCompanyId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));
        Drive d = new Drive();
        d.setDriveName(req.getDriveName().trim());
        d.setCompany(company);
        d.setRegistrationDeadline(req.getRegistrationDeadline());
        if (req.getPlacementCoordinatorId() != null) {
            PlacementCoordinator pc = coordinatorRepo.findById(req.getPlacementCoordinatorId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Coordinator not found"));
            d.setPlacementCoordinator(pc);
        }
        Drive saved = driveRepo.save(d);
        if (req.getAllowedBranches() != null) {
            for (BranchType b : req.getAllowedBranches()) {
                if (b == null) {
                    continue;
                }
                DriveBranch db = new DriveBranch();
                db.setDrive(saved);
                db.setBranch(b);
                saved.getAllowedBranches().add(db);
            }
        }
        if (req.getOfferedRoleTitles() != null) {
            for (String title : req.getOfferedRoleTitles()) {
                if (title == null || title.isBlank()) {
                    continue;
                }
                DriveOfferedRole dr = new DriveOfferedRole();
                dr.setDrive(saved);
                dr.setRoleTitle(title.trim());
                saved.getOfferedRoles().add(dr);
            }
        }
        saved = driveRepo.save(saved);
        saved.getAllowedBranches().size();
        saved.getOfferedRoles().size();
        return PlacementDtoMapper.toDriveResponse(saved);
    }

    @Transactional
    public DriveResponse update(Long id, DriveUpdateRequest req) {
        Drive d = driveRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Drive not found"));
        if (req.getDriveName() != null && !req.getDriveName().isBlank()) {
            d.setDriveName(req.getDriveName().trim());
        }
        if (req.getCompanyId() != null) {
            Company company = companyRepo.findById(req.getCompanyId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));
            d.setCompany(company);
        }
        if (req.getRegistrationDeadline() != null) {
            d.setRegistrationDeadline(req.getRegistrationDeadline());
        }
        if (req.getPlacementCoordinatorId() != null) {
            PlacementCoordinator pc = coordinatorRepo.findById(req.getPlacementCoordinatorId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Coordinator not found"));
            d.setPlacementCoordinator(pc);
        }
        if (req.getAllowedBranches() != null) {
            d.getAllowedBranches().clear();
            for (BranchType b : req.getAllowedBranches()) {
                if (b == null) {
                    continue;
                }
                DriveBranch db = new DriveBranch();
                db.setDrive(d);
                db.setBranch(b);
                d.getAllowedBranches().add(db);
            }
        }
        if (req.getOfferedRoleTitles() != null) {
            d.getOfferedRoles().clear();
            for (String title : req.getOfferedRoleTitles()) {
                if (title == null || title.isBlank()) {
                    continue;
                }
                DriveOfferedRole dr = new DriveOfferedRole();
                dr.setDrive(d);
                dr.setRoleTitle(title.trim());
                d.getOfferedRoles().add(dr);
            }
        }
        Drive saved = driveRepo.save(d);
        saved.getAllowedBranches().size();
        saved.getOfferedRoles().size();
        return PlacementDtoMapper.toDriveResponse(saved);
    }

    @Transactional(readOnly = true)
    public DriveResponse get(Long id) {
        Drive d = driveRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Drive not found"));
        d.getAllowedBranches().size();
        d.getOfferedRoles().size();
        return PlacementDtoMapper.toDriveResponse(d);
    }

    @Transactional(readOnly = true)
    public List<DriveResponse> findAll() {
        return driveRepo.findAll().stream().map(d -> {
            d.getAllowedBranches().size();
            d.getOfferedRoles().size();
            return PlacementDtoMapper.toDriveResponse(d);
        }).toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!driveRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drive not found");
        }
        driveRepo.deleteById(id);
    }
}
