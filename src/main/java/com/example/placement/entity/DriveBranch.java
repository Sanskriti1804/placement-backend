package com.example.placement.entity;

import com.example.placement.entity.main.DriveProfile;
import com.example.placement.common.enums.BranchType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(
        name = "drive_branches",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_drive_branch",
                columnNames = {"drive_id", "branch"}
        ),
        indexes = @Index(name = "idx_drive_branch_drive_id", columnList = "drive_id")
)
public class DriveBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "drive_id", nullable = false)
    @JsonIgnore
    private DriveProfile drive;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 64)
    private BranchType branch;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DriveProfile getDrive() {
        return drive;
    }

    public void setDrive(DriveProfile drive) {
        this.drive = drive;
    }

    public BranchType getBranch() {
        return branch;
    }

    public void setBranch(BranchType branch) {
        this.branch = branch;
    }
}
