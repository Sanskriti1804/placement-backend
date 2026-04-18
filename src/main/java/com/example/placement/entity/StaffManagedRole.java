package com.example.placement.entity;

import com.example.placement.common.enums.BranchType;
import com.example.placement.entity.main.StaffProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "staff_managed_roles")
public class StaffManagedRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    @JsonIgnore
    private StaffProfile staff;

    @ElementCollection
    @CollectionTable(name = "staff_managed_role_companies", joinColumns = @JoinColumn(name = "managed_role_id"))
    @Column(name = "company_id")
    private List<Long> companyIds = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "staff_managed_role_drives", joinColumns = @JoinColumn(name = "managed_role_id"))
    @Column(name = "drive_id")
    private List<Long> driveIds = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "staff_managed_role_students", joinColumns = @JoinColumn(name = "managed_role_id"))
    @Column(name = "student_id")
    private List<Long> studentIds = new ArrayList<>();

    @ElementCollection(targetClass = BranchType.class)
    @CollectionTable(name = "staff_managed_role_departments", joinColumns = @JoinColumn(name = "managed_role_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "department", length = 64)
    private List<BranchType> departments = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StaffProfile getStaff() {
        return staff;
    }

    public void setStaff(StaffProfile staff) {
        this.staff = staff;
    }

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
