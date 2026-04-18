package com.example.placement.entity;

import com.example.placement.entity.main.StaffProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(
        name = "staff_department_assignments",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_staff_department",
                columnNames = {"staff_profile_id", "department_id"}
        ),
        indexes = {
                @Index(name = "idx_sdepta_staff", columnList = "staff_profile_id"),
                @Index(name = "idx_sdepta_department", columnList = "department_id")
        }
)
public class StaffDepartmentAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_profile_id", nullable = false)
    @JsonIgnore
    private StaffProfile staff;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
