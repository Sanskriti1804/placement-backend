package com.example.placement.dto.placement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StaffProfileResponse {

    private Long id;
    private Long userId;
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
    private List<StaffCompanyAssignmentResponse> companyAssignments = new ArrayList<>();
    private List<StaffDriveAssignmentResponse> driveAssignments = new ArrayList<>();
    private List<StaffStudentAssignmentResponse> studentAssignments = new ArrayList<>();
    private List<StaffDepartmentAssignmentResponse> departmentAssignments = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public List<StaffCompanyAssignmentResponse> getCompanyAssignments() {
        return companyAssignments;
    }

    public void setCompanyAssignments(List<StaffCompanyAssignmentResponse> companyAssignments) {
        this.companyAssignments = companyAssignments;
    }

    public List<StaffDriveAssignmentResponse> getDriveAssignments() {
        return driveAssignments;
    }

    public void setDriveAssignments(List<StaffDriveAssignmentResponse> driveAssignments) {
        this.driveAssignments = driveAssignments;
    }

    public List<StaffStudentAssignmentResponse> getStudentAssignments() {
        return studentAssignments;
    }

    public void setStudentAssignments(List<StaffStudentAssignmentResponse> studentAssignments) {
        this.studentAssignments = studentAssignments;
    }

    public List<StaffDepartmentAssignmentResponse> getDepartmentAssignments() {
        return departmentAssignments;
    }

    public void setDepartmentAssignments(List<StaffDepartmentAssignmentResponse> departmentAssignments) {
        this.departmentAssignments = departmentAssignments;
    }
}
