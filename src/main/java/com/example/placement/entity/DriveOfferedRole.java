package com.example.placement.entity;

import com.example.placement.entity.main.DriveProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(
        name = "drive_offered_roles",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_drive_role_title",
                columnNames = {"drive_id", "role_title"}
        ),
        indexes = @Index(name = "idx_drive_role_drive_id", columnList = "drive_id")
)
public class DriveOfferedRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "drive_id", nullable = false)
    @JsonIgnore
    private DriveProfile drive;

    @Column(name = "role_title", nullable = false, length = 255)
    private String roleTitle;

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

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }
}
