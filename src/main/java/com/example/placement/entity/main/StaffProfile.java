package com.example.placement.entity.main;

import com.example.placement.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "staff_profiles",
        indexes = {
                @Index(name = "idx_staff_user_id", columnList = "user_id"),
                @Index(name = "idx_staff_email", columnList = "email"),
                @Index(name = "idx_staff_name", columnList = "name")
        }
)
public class StaffProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    @JsonIgnore
    private User user;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 320)
    private String email;

    @Column(name = "phone_number", length = 32)
    private String phoneNumber;

    @Column(name = "office_location", length = 512)
    private String officeLocation;

    @Column(name = "college_name", length = 255)
    private String collegeName;

    @Column(name = "faculty_duty", columnDefinition = "TEXT")
    private String facultyDuty;

    @Column(name = "placement_duty", columnDefinition = "TEXT")
    private String placementDuty;

    @Column(name = "staff_current_role", length = 255)
    private String currentRole;

    @Column(name = "placement_responsibilities", columnDefinition = "TEXT")
    private String placementResponsibilities;

    @Column(columnDefinition = "TEXT")
    private String qualification;

    @Column(columnDefinition = "TEXT")
    private String experience;

    @Column(name = "subjects_taught", columnDefinition = "TEXT")
    private String subjectsTaught;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StaffCompanyAssignment> companyAssignments = new ArrayList<>();

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StaffDriveAssignment> driveAssignments = new ArrayList<>();

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StaffStudentAssignment> studentAssignments = new ArrayList<>();

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StaffDepartmentAssignment> departmentAssignments = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<StaffCompanyAssignment> getCompanyAssignments() {
        return companyAssignments;
    }

    public void setCompanyAssignments(List<StaffCompanyAssignment> companyAssignments) {
        this.companyAssignments = companyAssignments;
    }

    public List<StaffDriveAssignment> getDriveAssignments() {
        return driveAssignments;
    }

    public void setDriveAssignments(List<StaffDriveAssignment> driveAssignments) {
        this.driveAssignments = driveAssignments;
    }

    public List<StaffStudentAssignment> getStudentAssignments() {
        return studentAssignments;
    }

    public void setStudentAssignments(List<StaffStudentAssignment> studentAssignments) {
        this.studentAssignments = studentAssignments;
    }

    public List<StaffDepartmentAssignment> getDepartmentAssignments() {
        return departmentAssignments;
    }

    public void setDepartmentAssignments(List<StaffDepartmentAssignment> departmentAssignments) {
        this.departmentAssignments = departmentAssignments;
    }
}
