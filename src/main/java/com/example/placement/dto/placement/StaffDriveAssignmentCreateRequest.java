package com.example.placement.dto.placement;

public class StaffDriveAssignmentCreateRequest {

    private Long staffProfileId;
    private Long driveId;

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
