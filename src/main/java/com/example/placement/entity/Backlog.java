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
}
