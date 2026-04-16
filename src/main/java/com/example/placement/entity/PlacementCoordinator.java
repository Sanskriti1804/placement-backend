package com.example.placement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "placement_coordinators",
        indexes = {
                @Index(name = "idx_pc_email", columnList = "email"),
                @Index(name = "idx_pc_name", columnList = "name")
        }
)
public class PlacementCoordinator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 320)
    private String email;

    /** LinkedIn InMail / profile URL or similar identifier. */
    @Column(name = "in_mail", length = 2048)
    private String inMail;

    @Column(name = "phone_number", length = 32)
    private String phoneNumber;

    @OneToMany(mappedBy = "placementCoordinator")
    @JsonIgnore
    private List<Job> jobs = new ArrayList<>();

    @OneToMany(mappedBy = "placementCoordinator")
    @JsonIgnore
    private List<Drive> drives = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getInMail() {
        return inMail;
    }

    public void setInMail(String inMail) {
        this.inMail = inMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Drive> getDrives() {
        return drives;
    }

    public void setDrives(List<Drive> drives) {
        this.drives = drives;
    }
}
