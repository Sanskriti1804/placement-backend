package com.example.placement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(
        name = "platform_links",
        uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "platform_type"})
)
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "platform_type", nullable = false)
    private PlatformType type;

    @Column(nullable = false)
    private String url;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private StudentProfile student;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public PlatformType getType() { return type; }
    public void setType(PlatformType type) { this.type = type; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public StudentProfile getStudent() { return student; }
    public void setStudent(StudentProfile student) { this.student = student; }
}
