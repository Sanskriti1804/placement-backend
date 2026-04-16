package com.example.placement.dto;

public class StudentProfileRequest {
    private Long userId;
    private String name;
    private String domainRole;
    private String phoneNumber;
    private String photoUrl;
    private String bio;
    private String addressLine;
    private String city;
    private String state;
    private java.time.LocalDate dob;
    private SkillRequest skills;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomainRole() {
        return domainRole;
    }

    public void setDomainRole(String domainRole) {
        this.domainRole = domainRole;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getter and Setter for photoUrl
    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    // Getter and Setter for bio
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public java.time.LocalDate getDob() {
        return dob;
    }

    public void setDob(java.time.LocalDate dob) {
        this.dob = dob;
    }

    public SkillRequest getSkills() {
        return skills;
    }

    public void setSkills(SkillRequest skills) {
        this.skills = skills;
    }
}