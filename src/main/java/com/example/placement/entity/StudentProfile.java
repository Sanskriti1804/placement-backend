package com.example.placement.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private String rollNo;
    private String userName;
    private String photoUrl;
    private String bio;

    @Enumerated(EnumType.STRING)
    private BranchType branch;

    @ManyToMany
    @JoinTable(
            name = "student_skills",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills = new HashSet<>();

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,  //any operation on StudentProfile will be applied to all the project object
            orphanRemoval = true    //if a proj is removed from this list - also will be deleted from the db
            )
    private List<Project> projects = new ArrayList<>();

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Platform> platforms = new ArrayList<>();

    //GETTERS AND SETTERS
    public Long getId(){
        return  id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getRollNo() { return rollNo; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public Set<Skill> getSkills(){return skills;}
    public void setSkills(Set<Skill> skills){this.skills = skills;}

    public List<Project> getProjects() { return projects; }
    public void setProjects(List<Project> projects) { this.projects = projects; }

    public List<Platform> getLinks() { return platforms; }
    public void setLinks(List<Platform> platforms) { this.platforms = platforms; }
}

