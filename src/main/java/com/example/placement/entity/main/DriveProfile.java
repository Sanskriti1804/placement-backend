package com.example.placement.entity.main;

import com.example.placement.entity.*;
import com.example.placement.entity.types.JobResultStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "drives",
        indexes = {
                @Index(name = "idx_drive_company_id", columnList = "company_id"),
                @Index(name = "idx_drive_coordinator_id", columnList = "placement_coordinator_id"),
                @Index(name = "idx_drive_reg_deadline", columnList = "registration_deadline")
        }
)
public class DriveProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "drive_name", nullable = false, length = 255)
    private String driveName;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    @JsonIgnore
    private CompanyProfile company;

    @Column(name = "registration_deadline", nullable = false)
    private LocalDateTime registrationDeadline;

    @Column(name = "drive_date_time")
    private LocalDateTime driveDateTime;

    @Column(length = 512)
    private String venue;

    @Enumerated(EnumType.STRING)
    @Column(name = "result_status", nullable = false, length = 32)
    private JobResultStatus resultStatus = JobResultStatus.NOT_ANNOUNCED;

    @Column(name = "result_date")
    private LocalDate resultDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "placement_coordinator_id")
    @JsonIgnore
    private PlacementCoordinator placementCoordinator;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "drive", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DriveBranch> allowedBranches = new ArrayList<>();

    @OneToMany(mappedBy = "drive", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DriveOfferedRole> offeredRoles = new ArrayList<>();

    @OneToMany(mappedBy = "drive", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DriveSelectionRound> selectionRounds = new ArrayList<>();

    @PrePersist
    void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        if (createdAt == null) {
            createdAt = now;
        }
        updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

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

    public CompanyProfile getCompany() {
        return company;
    }

    public void setCompany(CompanyProfile company) {
        this.company = company;
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

    public PlacementCoordinator getPlacementCoordinator() {
        return placementCoordinator;
    }

    public void setPlacementCoordinator(PlacementCoordinator placementCoordinator) {
        this.placementCoordinator = placementCoordinator;
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

    public List<DriveBranch> getAllowedBranches() {
        return allowedBranches;
    }

    public void setAllowedBranches(List<DriveBranch> allowedBranches) {
        this.allowedBranches = allowedBranches;
    }

    public List<DriveOfferedRole> getOfferedRoles() {
        return offeredRoles;
    }

    public void setOfferedRoles(List<DriveOfferedRole> offeredRoles) {
        this.offeredRoles = offeredRoles;
    }

    public List<DriveSelectionRound> getSelectionRounds() {
        return selectionRounds;
    }

    public void setSelectionRounds(List<DriveSelectionRound> selectionRounds) {
        this.selectionRounds = selectionRounds;
    }
}
