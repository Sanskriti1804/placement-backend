package com.example.placement.entity;

import jakarta.persistence.*;

@Entity
public class Backlog {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "education_profile_id")
    private EducationProfile educationProfile;

    private String subject;
    private Integer semester;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EducationProfile getEducationProfile() {
        return educationProfile;
    }

    public void setEducationProfile(EducationProfile educationProfile) {
        this.educationProfile = educationProfile;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}
