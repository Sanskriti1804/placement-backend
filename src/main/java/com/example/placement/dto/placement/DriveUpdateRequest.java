package com.example.placement.dto.placement;

import com.example.placement.entity.BranchType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DriveUpdateRequest {

    private String driveName;
    private Long companyId;
    private LocalDateTime registrationDeadline;
    private Long placementCoordinatorId;
    private List<BranchType> allowedBranches = new ArrayList<>();
    private List<String> offeredRoleTitles = new ArrayList<>();

    public String getDriveName() {
        return driveName;
    }

    public void setDriveName(String driveName) {
        this.driveName = driveName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public LocalDateTime getRegistrationDeadline() {
        return registrationDeadline;
    }

    public void setRegistrationDeadline(LocalDateTime registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    public Long getPlacementCoordinatorId() {
        return placementCoordinatorId;
    }

    public void setPlacementCoordinatorId(Long placementCoordinatorId) {
        this.placementCoordinatorId = placementCoordinatorId;
    }

    public List<BranchType> getAllowedBranches() {
        return allowedBranches;
    }

    public void setAllowedBranches(List<BranchType> allowedBranches) {
        this.allowedBranches = allowedBranches;
    }

    public List<String> getOfferedRoleTitles() {
        return offeredRoleTitles;
    }

    public void setOfferedRoleTitles(List<String> offeredRoleTitles) {
        this.offeredRoleTitles = offeredRoleTitles;
    }
}
