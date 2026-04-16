package com.example.placement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

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
public class Drive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "drive_name", nullable = false, length = 255)
    private String driveName;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    @JsonIgnore
    private Company company;

    @Column(name = "registration_deadline", nullable = false)
    private LocalDateTime registrationDeadline;

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public LocalDateTime getRegistrationDeadline() {
        return registrationDeadline;
    }

    public void setRegistrationDeadline(LocalDateTime registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
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
}
