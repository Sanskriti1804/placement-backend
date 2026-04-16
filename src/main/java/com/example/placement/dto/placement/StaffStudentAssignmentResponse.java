package com.example.placement.dto.placement;

public class StaffStudentAssignmentResponse {

    private Long id;
    private Long staffProfileId;
    private Long studentProfileId;

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

    public Long getStudentProfileId() {
        return studentProfileId;
    }

    public void setStudentProfileId(Long studentProfileId) {
        this.studentProfileId = studentProfileId;
    }
}
