package com.example.placement.dto.placement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StaffProfileUpdateRequest {

    private String name;
    private String email;
    private String phoneNumber;
    private String officeLocation;
    private String collegeName;
    private String facultyDuty;
    private String placementDuty;
    private String currentRole;
    private String placementResponsibilities;
    private String qualification;
    private String experience;
    private String subjectsTaught;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Long> assignedCompanyIds = new ArrayList<>();
    private List<Long> assignedDriveIds = new ArrayList<>();
    private List<Long> assignedStudentProfileIds = new ArrayList<>();
    private List<Long> assignedDepartmentIds = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getFacultyDuty() {
        return facultyDuty;
    }

    public void setFacultyDuty(String facultyDuty) {
        this.facultyDuty = facultyDuty;
    }

    public String getPlacementDuty() {
        return placementDuty;
    }

    public void setPlacementDuty(String placementDuty) {
        this.placementDuty = placementDuty;
    }

    public String getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(String currentRole) {
        this.currentRole = currentRole;
    }

    public String getPlacementResponsibilities() {
        return placementResponsibilities;
    }

    public void setPlacementResponsibilities(String placementResponsibilities) {
        this.placementResponsibilities = placementResponsibilities;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSubjectsTaught() {
        return subjectsTaught;
    }

    public void setSubjectsTaught(String subjectsTaught) {
        this.subjectsTaught = subjectsTaught;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Long> getAssignedCompanyIds() {
        return assignedCompanyIds;
    }

    public void setAssignedCompanyIds(List<Long> assignedCompanyIds) {
        this.assignedCompanyIds = assignedCompanyIds;
    }

    public List<Long> getAssignedDriveIds() {
        return assignedDriveIds;
    }

    public void setAssignedDriveIds(List<Long> assignedDriveIds) {
        this.assignedDriveIds = assignedDriveIds;
    }

    public List<Long> getAssignedStudentProfileIds() {
        return assignedStudentProfileIds;
    }

    public void setAssignedStudentProfileIds(List<Long> assignedStudentProfileIds) {
        this.assignedStudentProfileIds = assignedStudentProfileIds;
    }

    public List<Long> getAssignedDepartmentIds() {
        return assignedDepartmentIds;
    }

    public void setAssignedDepartmentIds(List<Long> assignedDepartmentIds) {
        this.assignedDepartmentIds = assignedDepartmentIds;
    }
}
