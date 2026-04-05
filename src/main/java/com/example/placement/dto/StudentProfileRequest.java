package com.example.placement.dto;

import java.util.Set;

public class StudentProfileRequest {
    private Long userId;
    private String rollNo;
    private String photoUrl;
    private String bio;
    private Set<String> skills;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // Getter and Setter for rollNo
    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
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

    // Getter and Setter for skills
    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }
}

//NO Skill DTO because Skill is treated as a simple reusable tag (using names as input),
// not a full API resource—you only need a DTO if you expose it separately (e.g., with IDs, endpoints, or responses).
//You’re sending:{ "skills": ["Java", "Spring"]}
//NOT: {  "skills": [  { "id": 1, "name": "Java" } ]}