package com.example.placement.dto.staff;

import com.example.placement.common.enums.BranchType;

import java.util.ArrayList;
import java.util.List;

public class StaffManagedRoleRequest {

    private List<Long> companyIds = new ArrayList<>();
    private List<Long> driveIds = new ArrayList<>();
    private List<Long> studentIds = new ArrayList<>();
    private List<BranchType> departments = new ArrayList<>();

    public List<Long> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<Long> companyIds) {
        this.companyIds = companyIds;
    }

    public List<Long> getDriveIds() {
        return driveIds;
    }

    public void setDriveIds(List<Long> driveIds) {
        this.driveIds = driveIds;
    }

    public List<Long> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Long> studentIds) {
        this.studentIds = studentIds;
    }

    public List<BranchType> getDepartments() {
        return departments;
    }

    public void setDepartments(List<BranchType> departments) {
        this.departments = departments;
    }
}
