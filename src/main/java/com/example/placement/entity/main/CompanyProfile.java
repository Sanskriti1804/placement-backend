package com.example.placement.entity.main;

import com.example.placement.entity.Industry;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "companies",
        indexes = {
                @Index(name = "idx_company_industry_id", columnList = "industry_id"),
                @Index(name = "idx_company_name", columnList = "name"),
                @Index(name = "idx_company_email", columnList = "email")
        }
)
public class CompanyProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 512)
    private String tagline;

    @Column(length = 512)
    private String location;

    @Column(length = 320)
    private String email;

    @Column(name = "website_url", length = 2048)
    private String websiteUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "industry_id")
    private Industry industry;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url", length = 2048)
    private String imageUrl;

    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<JobProfile> jobs = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<DriveProfile> drives = new ArrayList<>();

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

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<JobProfile> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobProfile> jobs) {
        this.jobs = jobs;
    }

    public List<DriveProfile> getDrives() {
        return drives;
    }

    public void setDrives(List<DriveProfile> drives) {
        this.drives = drives;
    }
}
