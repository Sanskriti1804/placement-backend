package com.example.placement.entity;

import jakarta.persistence.*;

/**
 * Lookup table for company industry classification (normalized; avoids repeated free-text).
 */
@Entity
@Table(
        name = "industries",
        indexes = {
                @Index(name = "idx_industry_name", columnList = "name"),
                @Index(name = "idx_industry_code", columnList = "code")
        }
)
public class Industry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 160)
    private String name;

    @Column(length = 64, unique = true)
    private String code;

    @Column(length = 512)
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
