package com.example.placement.service.crud;

import com.example.placement.dto.placement.DriveCreateRequest;
import com.example.placement.dto.placement.DriveResponse;
import com.example.placement.dto.placement.DriveSelectionRoundResponse;
import com.example.placement.dto.placement.DriveUpdateRequest;
import com.example.placement.dto.placement.JobSelectionRoundCreateRequest;
import com.example.placement.dto.placement.JobSelectionRoundUpdateRequest;
import com.example.placement.entity.types.BranchType;
import com.example.placement.entity.types.JobResultStatus;
import com.example.placement.entity.main.CompanyProfile;
import com.example.placement.entity.main.DriveProfile;
import com.example.placement.entity.DriveBranch;
import com.example.placement.entity.DriveOfferedRole;
import com.example.placement.entity.DriveSelectionRound;
import com.example.placement.entity.PlacementCoordinator;
import com.example.placement.entity.RoundCompletionStatus;
import com.example.placement.repository.CompanyRepo;
import com.example.placement.repository.DriveRepo;
import com.example.placement.repository.DriveSelectionRoundRepo;
import com.example.placement.repository.PlacementCoordinatorRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class DriveCrudService {

    private static final int MAX_DRIVE_BRANCHES = 3;
    private static final int MAX_DRIVE_ROLES = 4;

    private final DriveRepo driveRepo;
    private final CompanyRepo companyRepo;
    private final PlacementCoordinatorRepo coordinatorRepo;
    private final DriveSelectionRoundRepo driveSelectionRoundRepo;

    public DriveCrudService(
            DriveRepo driveRepo,
            CompanyRepo companyRepo,
            PlacementCoordinatorRepo coordinatorRepo,
            DriveSelectionRoundRepo driveSelectionRoundRepo
    ) {
        this.driveRepo = driveRepo;
        this.companyRepo = companyRepo;
        this.coordinatorRepo = coordinatorRepo;
        this.driveSelectionRoundRepo = driveSelectionRoundRepo;
    }

    private static long distinctBranchCount(List<BranchType> allowedBranches) {
        if (allowedBranches == null) {
            return 0;
        }
        return allowedBranches.stream().filter(Objects::nonNull).distinct().count();
    }

    private static long distinctNonBlankRoleTitleCount(List<String> titles) {
        if (titles == null) {
            return 0;
        }
        return titles.stream()
                .filter(t -> t != null && !t.isBlank())
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .distinct()
                .count();
    }

    private void validateBranchesAndRoles(List<BranchType> branches, List<String> roleTitles) {
        long bc = distinctBranchCount(branches);
        if (bc > MAX_DRIVE_BRANCHES) {
            throw new IllegalArgumentException("A drive may have at most " + MAX_DRIVE_BRANCHES + " allowed branches");
        }
        long rc = distinctNonBlankRoleTitleCount(roleTitles);
        if (rc > MAX_DRIVE_ROLES) {
            throw new IllegalArgumentException("A drive may have at most " + MAX_DRIVE_ROLES + " offered roles");
        }
    }

    private void normalizeResultFields(DriveProfile d) {
        if (d.getResultStatus() == null) {
            d.setResultStatus(JobResultStatus.NOT_ANNOUNCED);
        }
        if (d.getResultStatus() == JobResultStatus.NOT_ANNOUNCED) {
            d.setResultDate(null);
        }
    }

    private void validateResultFields(DriveProfile d) {
        if (d.getResultStatus() == JobResultStatus.ANNOUNCED && d.getResultDate() == null) {
            throw new IllegalArgumentException("resultDate is required when resultStatus is ANNOUNCED");
        }
    }

    @Transactional
    public DriveResponse create(DriveCreateRequest req) {
        if (req.getDriveName() == null || req.getDriveName().isBlank()
                || req.getCompanyId() == null || req.getRegistrationDeadline() == null) {
            throw new IllegalArgumentException("driveName, companyId, and registrationDeadline are required");
        }
        if (req.getPlacementCoordinatorId() == null) {
            throw new IllegalArgumentException("placementCoordinatorId is required");
        }
        validateBranchesAndRoles(req.getAllowedBranches(), req.getOfferedRoleTitles());
        CompanyProfile company = companyRepo.findById(req.getCompanyId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));
        PlacementCoordinator pc = coordinatorRepo.findById(req.getPlacementCoordinatorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Coordinator not found"));
        DriveProfile d = new DriveProfile();
        d.setDriveName(req.getDriveName().trim());
        d.setCompany(company);
        d.setRegistrationDeadline(req.getRegistrationDeadline());
        d.setDriveDateTime(req.getDriveDateTime());
        d.setVenue(req.getVenue());
        d.setResultStatus(req.getResultStatus() != null ? req.getResultStatus() : JobResultStatus.NOT_ANNOUNCED);
        d.setResultDate(req.getResultDate());
        d.setPlacementCoordinator(pc);
        normalizeResultFields(d);
        validateResultFields(d);
        DriveProfile saved = driveRepo.save(d);
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
        if (req.getSelectionRounds() != null) {
            for (JobSelectionRoundCreateRequest rc : req.getSelectionRounds()) {
                if (rc.getRoundName() == null || rc.getRoundName().isBlank() || rc.getSequenceOrder() == null) {
                    throw new IllegalArgumentException("Each selection round requires roundName and sequenceOrder");
                }
                DriveSelectionRound sr = new DriveSelectionRound();
                sr.setDrive(saved);
                sr.setRoundName(rc.getRoundName().trim());
                sr.setSequenceOrder(rc.getSequenceOrder());
                sr.setScheduledDate(rc.getScheduledDate());
                sr.setCompletionStatus(rc.getCompletionStatus() != null ? rc.getCompletionStatus() : RoundCompletionStatus.PENDING);
                saved.getSelectionRounds().add(sr);
            }
        }
        saved = driveRepo.save(saved);
        saved.getAllowedBranches().size();
        saved.getOfferedRoles().size();
        saved.getSelectionRounds().size();
        return PlacementDtoMapper.toDriveResponse(saved);
    }

    @Transactional
    public DriveResponse update(Long id, DriveUpdateRequest req) {
        DriveProfile d = driveRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Drive not found"));
        if (req.getAllowedBranches() != null || req.getOfferedRoleTitles() != null) {
            validateBranchesAndRoles(
                    req.getAllowedBranches() != null ? req.getAllowedBranches() : d.getAllowedBranches().stream()
                            .map(DriveBranch::getBranch)
                            .toList(),
                    req.getOfferedRoleTitles() != null ? req.getOfferedRoleTitles() : d.getOfferedRoles().stream()
                            .map(DriveOfferedRole::getRoleTitle)
                            .toList()
            );
        }
        if (req.getDriveName() != null && !req.getDriveName().isBlank()) {
            d.setDriveName(req.getDriveName().trim());
        }
        if (req.getCompanyId() != null) {
            CompanyProfile company = companyRepo.findById(req.getCompanyId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));
            d.setCompany(company);
        }
        if (req.getRegistrationDeadline() != null) {
            d.setRegistrationDeadline(req.getRegistrationDeadline());
        }
        if (req.getDriveDateTime() != null) {
            d.setDriveDateTime(req.getDriveDateTime());
        }
        if (req.getVenue() != null) {
            d.setVenue(req.getVenue());
        }
        if (req.getResultStatus() != null) {
            d.setResultStatus(req.getResultStatus());
        }
        if (req.getResultDate() != null) {
            d.setResultDate(req.getResultDate());
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
        if (req.getSelectionRounds() != null) {
            d.getSelectionRounds().clear();
            for (JobSelectionRoundUpdateRequest ur : req.getSelectionRounds()) {
                if (ur.getRoundName() == null || ur.getRoundName().isBlank() || ur.getSequenceOrder() == null) {
                    throw new IllegalArgumentException("Each selection round requires roundName and sequenceOrder");
                }
                DriveSelectionRound sr = new DriveSelectionRound();
                sr.setDrive(d);
                sr.setRoundName(ur.getRoundName().trim());
                sr.setSequenceOrder(ur.getSequenceOrder());
                sr.setScheduledDate(ur.getScheduledDate());
                sr.setCompletionStatus(ur.getCompletionStatus() != null ? ur.getCompletionStatus() : RoundCompletionStatus.PENDING);
                d.getSelectionRounds().add(sr);
            }
        }
        normalizeResultFields(d);
        validateResultFields(d);
        DriveProfile saved = driveRepo.save(d);
        saved.getAllowedBranches().size();
        saved.getOfferedRoles().size();
        saved.getSelectionRounds().size();
        return PlacementDtoMapper.toDriveResponse(saved);
    }

    @Transactional(readOnly = true)
    public DriveResponse get(Long id) {
        DriveProfile d = driveRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Drive not found"));
        d.getAllowedBranches().size();
        d.getOfferedRoles().size();
        d.getSelectionRounds().size();
        return PlacementDtoMapper.toDriveResponse(d);
    }

    @Transactional(readOnly = true)
    public List<DriveResponse> findAll() {
        return driveRepo.findAll().stream().map(d -> {
            d.getAllowedBranches().size();
            d.getOfferedRoles().size();
            d.getSelectionRounds().size();
            return PlacementDtoMapper.toDriveResponse(d);
        }).toList();
    }

    @Transactional(readOnly = true)
    public List<DriveSelectionRoundResponse> listSelectionRoundsForDrive(Long driveId) {
        if (!driveRepo.existsById(driveId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drive not found");
        }
        return driveSelectionRoundRepo.findByDrive_IdOrderBySequenceOrderAsc(driveId).stream()
                .map(PlacementDtoMapper::toDriveSelectionRoundResponse)
                .toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!driveRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drive not found");
        }
        driveRepo.deleteById(id);
    }
}
