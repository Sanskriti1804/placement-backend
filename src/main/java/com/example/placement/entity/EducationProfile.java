package com.example.placement.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class EducationProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "student_id", unique = true)
    private StudentProfile student;

    private String university;

    @Enumerated(EnumType.STRING)
    private BranchType branch;

    @Enumerated(EnumType.STRING)
    private CourseType course;

    @Enumerated(EnumType.STRING)
    private DomainType domain;

    private Integer currentYear;

    @Column(precision = 4, scale = 2, nullable = false)
    private BigDecimal tenthPercentage;
    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal twelfthPercentage;
    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal currentCgpa;


    private String backlogSubject;
    private Integer backlogSemester;

    private Integer gapYears;
    private String gapReason;


}
