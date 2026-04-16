package com.example.placement.entity;

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
}

