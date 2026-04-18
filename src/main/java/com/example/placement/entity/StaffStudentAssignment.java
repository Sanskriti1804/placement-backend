package com.example.placement.entity;

import com.example.placement.entity.main.StaffProfile;
import com.example.placement.entity.main.StudentProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(
        name = "staff_student_assignments",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_staff_student",
                columnNames = {"staff_profile_id", "student_profile_id"}
        ),
        indexes = {
                @Index(name = "idx_ssa_staff", columnList = "staff_profile_id"),
                @Index(name = "idx_ssa_student", columnList = "student_profile_id")
        }
)
public class StaffStudentAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_profile_id", nullable = false)
    @JsonIgnore
    private StaffProfile staff;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_profile_id", nullable = false)
    private StudentProfile student;

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

    public StudentProfile getStudent() {
        return student;
    }

    public void setStudent(StudentProfile student) {
        this.student = student;
    }
}
