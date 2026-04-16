package com.example.placement.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "departments",
        indexes = {
                @Index(name = "idx_department_name", columnList = "name"),
                @Index(name = "idx_department_code", columnList = "code")
        }
)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 64, unique = true)
    private String code;

    @Column(name = "college_name", length = 255)
    private String collegeName;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
