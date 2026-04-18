package com.example.placement.entity.main;

import com.example.placement.entity.StaffManagedRole;
import com.example.placement.entity.StaffProfessionalExperience;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

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

    @Column(name = "user_email", length = 320)
    private String userEmail;

    @Column(nullable = false, length = 320)
    private String email;

    @Column(name = "phone_number", length = 32)
    private String phoneNumber;

    @Column(name = "linkedin", length = 2048)
    private String linkedin;

    @Column(name = "office_location", length = 512)
    private String officeLocation;

    @Column(name = "college_name", length = 255)
    private String collegeName;

    @Column(name = "joining_year")
    private Integer joiningYear;

    @Column(name = "joining_month")
    private Integer joiningMonth;

    @Column(name = "ending_year")
    private Integer endingYear;

    @Column(name = "ending_month")
    private Integer endingMonth;

    @ElementCollection
    @CollectionTable(name = "staff_subjects", joinColumns = @JoinColumn(name = "staff_id"))
    @Column(name = "subject_line", length = 512)
    private List<String> subjects = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "staff_qualifications", joinColumns = @JoinColumn(name = "staff_id"))
    @Column(name = "qualification_line", length = 512)
    private List<String> qualifications = new ArrayList<>();

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StaffProfessionalExperience> professionalExperiences = new ArrayList<>();

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StaffManagedRole> managedRoles = new ArrayList<>();

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

    public List<StaffProfessionalExperience> getProfessionalExperiences() {
        return professionalExperiences;
    }

    public void setProfessionalExperiences(List<StaffProfessionalExperience> professionalExperiences) {
        this.professionalExperiences = professionalExperiences;
    }

    public List<StaffManagedRole> getManagedRoles() {
        return managedRoles;
    }

    public void setManagedRoles(List<StaffManagedRole> managedRoles) {
        this.managedRoles = managedRoles;
    }
}
