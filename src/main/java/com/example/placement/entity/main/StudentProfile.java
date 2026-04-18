package com.example.placement.entity.main;

import com.example.placement.entity.JobApplication;
import com.example.placement.entity.Platform;
import com.example.placement.entity.Project;
import com.example.placement.entity.Skill;
import com.example.placement.entity.StudentExperience;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student_profile")
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    private String name;

    @Column(length = 128)
    private String username;

    @Column(name = "user_email", length = 320)
    private String userEmail;

    @Column(name = "domain_role")
    private String domainRole;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String photoUrl;
    private String bio;
    @Column(name = "address_line")
    private String addressLine;
    private String city;
    private String state;

    @Column(name = "pin_code", length = 16)
    private String pinCode;

    @Column(name = "resume_url", length = 2048)
    private String resumeUrl;

    @Column(nullable = false)
    private boolean hired;

    @Column(name = "hired_company_name", length = 255)
    private String hiredCompanyName;

    private LocalDate dob;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Skill skills;

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,  //any operation on StudentProfile will be applied to all the project object
            orphanRemoval = true    //if a proj is removed from this list - also will be deleted from the db
            )
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Platform> platformLinks = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobApplication> applications = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentExperience> experiences = new ArrayList<>();

    //GETTERS AND SETTERS
    public Long getId(){
        return  id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDomainRole() { return domainRole; }
    public void setDomainRole(String domainRole) { this.domainRole = domainRole; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public Skill getSkills() { return skills; }
    public void setSkills(Skill skills) { this.skills = skills; }

    public List<Project> getProjects() { return projects; }
    public void setProjects(List<Project> projects) { this.projects = projects; }

    public String getAddressLine() { return addressLine; }
    public void setAddressLine(String addressLine) { this.addressLine = addressLine; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public List<Platform> getPlatformLinks() { return platformLinks; }
    public void setPlatformLinks(List<Platform> platformLinks) { this.platformLinks = platformLinks; }

    public List<JobApplication> getApplications() {
        return applications;
    }

    public void setApplications(List<JobApplication> applications) {
        this.applications = applications;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public boolean isHired() {
        return hired;
    }

    public void setHired(boolean hired) {
        this.hired = hired;
    }

    public String getHiredCompanyName() {
        return hiredCompanyName;
    }

    public void setHiredCompanyName(String hiredCompanyName) {
        this.hiredCompanyName = hiredCompanyName;
    }

    public List<StudentExperience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<StudentExperience> experiences) {
        this.experiences = experiences;
    }
}

