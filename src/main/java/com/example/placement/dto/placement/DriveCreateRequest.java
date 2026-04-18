package com.example.placement.dto.placement;

import com.example.placement.entity.types.BranchType;
import com.example.placement.entity.types.JobResultStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DriveCreateRequest {

    private String driveName;
    private Long companyId;
    private LocalDateTime registrationDeadline;
    private LocalDateTime driveDateTime;
    private String venue;
    private JobResultStatus resultStatus;
    private LocalDate resultDate;
    private Long placementCoordinatorId;
    private List<BranchType> allowedBranches = new ArrayList<>();
    private List<String> offeredRoleTitles = new ArrayList<>();
    private List<JobSelectionRoundCreateRequest> selectionRounds = new ArrayList<>();

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

    public LocalDateTime getDriveDateTime() {
        return driveDateTime;
    }

    public void setDriveDateTime(LocalDateTime driveDateTime) {
        this.driveDateTime = driveDateTime;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public JobResultStatus getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(JobResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }

    public LocalDate getResultDate() {
        return resultDate;
    }

    public void setResultDate(LocalDate resultDate) {
        this.resultDate = resultDate;
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

    public List<JobSelectionRoundCreateRequest> getSelectionRounds() {
        return selectionRounds;
    }

    public void setSelectionRounds(List<JobSelectionRoundCreateRequest> selectionRounds) {
        this.selectionRounds = selectionRounds;
    }
}
