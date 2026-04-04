package com.example.placement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    //Owning Side : Student profile entity - add/del should be done in this side - connected w a field name - "skills"
    //Inverse/ Non-owning side : Skill entit y
    @ManyToMany(mappedBy = "skills")
    @JsonIgnore
    //Set - no duplicate skill for a student
    private Set<StudentProfile> students = new HashSet<>();     //initialized - w/o null checks

    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
