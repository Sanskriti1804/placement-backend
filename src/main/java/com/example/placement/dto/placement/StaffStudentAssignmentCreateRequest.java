package com.example.placement.dto.placement;

public class StaffStudentAssignmentCreateRequest {

    private Long staffProfileId;
    private Long studentProfileId;

    public Long getStaffProfileId() {
        return staffProfileId;
    }

    public void setStaffProfileId(Long staffProfileId) {
        this.staffProfileId = staffProfileId;
    }

    public Long getStudentProfileId() {
        return studentProfileId;
    }

    public void setStudentProfileId(Long studentProfileId) {
        this.studentProfileId = studentProfileId;
    }
}
