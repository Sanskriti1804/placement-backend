package com.example.placement.dto.placement;

public class StaffDriveAssignmentResponse {

    private Long id;
    private Long staffProfileId;
    private Long driveId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStaffProfileId() {
        return staffProfileId;
    }

    public void setStaffProfileId(Long staffProfileId) {
        this.staffProfileId = staffProfileId;
    }

    public Long getDriveId() {
        return driveId;
    }

    public void setDriveId(Long driveId) {
        this.driveId = driveId;
    }
}
