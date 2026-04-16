package com.example.placement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(
        name = "staff_company_assignments",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_staff_company",
                columnNames = {"staff_profile_id", "company_id"}
        ),
        indexes = {
                @Index(name = "idx_sca_staff", columnList = "staff_profile_id"),
                @Index(name = "idx_sca_company", columnList = "company_id")
        }
)
public class StaffCompanyAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_profile_id", nullable = false)
    @JsonIgnore
    private StaffProfile staff;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
