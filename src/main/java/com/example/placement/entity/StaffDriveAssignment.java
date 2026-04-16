package com.example.placement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(
        name = "staff_drive_assignments",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_staff_drive",
                columnNames = {"staff_profile_id", "drive_id"}
        ),
        indexes = {
                @Index(name = "idx_sda_staff", columnList = "staff_profile_id"),
                @Index(name = "idx_sda_drive", columnList = "drive_id")
        }
)
public class StaffDriveAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_profile_id", nullable = false)
    @JsonIgnore
    private StaffProfile staff;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "drive_id", nullable = false)
    private Drive drive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StaffProfile getStaff() {
        return staff;
    }

    public void setStaff(StaffProfile staff) {
        this.staff = staff;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }
}
