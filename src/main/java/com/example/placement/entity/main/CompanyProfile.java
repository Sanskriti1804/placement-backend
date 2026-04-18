package com.example.placement.entity.main;

import com.example.placement.entity.CompanyContactSupport;
import com.example.placement.entity.JobApplication;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "companies",
        indexes = {
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

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String overview;

    @Column(length = 512)
    private String sector;

    @Column(name = "image_url", length = 2048)
    private String imageUrl;

    @ElementCollection
    @CollectionTable(name = "company_documents", joinColumns = @JoinColumn(name = "company_id"))
    @Column(name = "document_url", length = 2048)
    private List<String> documentUrls = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompanyContactSupport> contactSupports = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<JobProfile> jobs = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<DriveProfile> drives = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<JobApplication> applications = new ArrayList<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getDocumentUrls() {
        return documentUrls;
    }

    public void setDocumentUrls(List<String> documentUrls) {
        this.documentUrls = documentUrls;
    }

    public List<CompanyContactSupport> getContactSupports() {
        return contactSupports;
    }

    public void setContactSupports(List<CompanyContactSupport> contactSupports) {
        this.contactSupports = contactSupports;
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

    public List<JobApplication> getApplications() {
        return applications;
    }

    public void setApplications(List<JobApplication> applications) {
        this.applications = applications;
    }
}
