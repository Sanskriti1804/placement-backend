package com.example.placement.dto.staff;

import java.util.ArrayList;
import java.util.List;

public class StaffProfileResponse {

    private Long id;
    private Long userId;
    private String name;
    private String userEmail;
    private String email;
    private String phoneNumber;
    private String linkedin;
    private String officeLocation;
    private String collegeName;
    private Integer joiningYear;
    private Integer joiningMonth;
    private Integer endingYear;
    private Integer endingMonth;
    private List<String> subjects = new ArrayList<>();
    private List<String> qualifications = new ArrayList<>();
    private List<StaffProfessionalExperienceResponse> professionalExperiences = new ArrayList<>();
    private List<StaffManagedRoleResponse> managedRoles = new ArrayList<>();

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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
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

    public Integer getJoiningYear() {
        return joiningYear;
    }

    public void setJoiningYear(Integer joiningYear) {
        this.joiningYear = joiningYear;
    }

    public Integer getJoiningMonth() {
        return joiningMonth;
    }

    public void setJoiningMonth(Integer joiningMonth) {
        this.joiningMonth = joiningMonth;
    }

    public Integer getEndingYear() {
        return endingYear;
    }

    public void setEndingYear(Integer endingYear) {
        this.endingYear = endingYear;
    }

    public Integer getEndingMonth() {
        return endingMonth;
    }

    public void setEndingMonth(Integer endingMonth) {
        this.endingMonth = endingMonth;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<String> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<String> qualifications) {
        this.qualifications = qualifications;
    }

    public List<StaffProfessionalExperienceResponse> getProfessionalExperiences() {
        return professionalExperiences;
    }

    public void setProfessionalExperiences(List<StaffProfessionalExperienceResponse> professionalExperiences) {
        this.professionalExperiences = professionalExperiences;
    }

    public List<StaffManagedRoleResponse> getManagedRoles() {
        return managedRoles;
    }

    public void setManagedRoles(List<StaffManagedRoleResponse> managedRoles) {
        this.managedRoles = managedRoles;
    }
}
