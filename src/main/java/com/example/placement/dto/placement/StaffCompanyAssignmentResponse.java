package com.example.placement.dto.placement;

public class StaffCompanyAssignmentResponse {

    private Long id;
    private Long staffProfileId;
    private Long companyId;

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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
