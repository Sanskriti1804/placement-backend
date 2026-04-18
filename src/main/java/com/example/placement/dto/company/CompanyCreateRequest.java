package com.example.placement.dto.company;

import java.util.ArrayList;
import java.util.List;

public class CompanyCreateRequest {

    private String name;
    private String tagline;
    private String location;
    private String email;
    private String websiteUrl;
    private String description;
    private String overview;
    private String sector;
    private String imageUrl;
    private List<String> documentUrls = new ArrayList<>();
    private List<CompanyContactSupportRequest> contactSupports = new ArrayList<>();

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

    public List<CompanyContactSupportRequest> getContactSupports() {
        return contactSupports;
    }

    public void setContactSupports(List<CompanyContactSupportRequest> contactSupports) {
        this.contactSupports = contactSupports;
    }
}
