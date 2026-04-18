package com.example.placement.dto.placement;

import com.example.placement.entity.types.JobResultStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DriveResponse {

    private Long id;
    private String driveName;
    private Long companyId;
    private LocalDateTime registrationDeadline;
    private LocalDateTime driveDateTime;
    private String venue;
    private JobResultStatus resultStatus;
    private LocalDate resultDate;
    private String resultDisplay;
    private Long placementCoordinatorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<DriveBranchResponse> allowedBranches = new ArrayList<>();
    private List<DriveOfferedRoleResponse> offeredRoles = new ArrayList<>();
    private List<DriveSelectionRoundResponse> selectionRounds = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getResultDisplay() {
        return resultDisplay;
    }

    public void setResultDisplay(String resultDisplay) {
        this.resultDisplay = resultDisplay;
    }

    public Long getPlacementCoordinatorId() {
        return placementCoordinatorId;
    }

    public void setPlacementCoordinatorId(Long placementCoordinatorId) {
        this.placementCoordinatorId = placementCoordinatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<DriveBranchResponse> getAllowedBranches() {
        return allowedBranches;
    }

    public void setAllowedBranches(List<DriveBranchResponse> allowedBranches) {
        this.allowedBranches = allowedBranches;
    }

    public List<DriveOfferedRoleResponse> getOfferedRoles() {
        return offeredRoles;
    }

    public void setOfferedRoles(List<DriveOfferedRoleResponse> offeredRoles) {
        this.offeredRoles = offeredRoles;
    }

    public List<DriveSelectionRoundResponse> getSelectionRounds() {
        return selectionRounds;
    }

    public void setSelectionRounds(List<DriveSelectionRoundResponse> selectionRounds) {
        this.selectionRounds = selectionRounds;
    }
}
