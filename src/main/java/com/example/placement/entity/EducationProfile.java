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


    //getters and setters
    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return id;
    }
    public StudentProfile getStudent() {
        return student;
    }

    public void setStudent(StudentProfile student) {
        this.student = student;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public BranchType getBranch() {
        return branch;
    }

    public void setBranch(BranchType branch) {
        this.branch = branch;
    }

    public CourseType getCourse() {
        return course;
    }

    public void setCourse(CourseType course) {
        this.course = course;
    }

    public DomainType getDomain() {
        return domain;
    }

    public void setDomain(DomainType domain) {
        this.domain = domain;
    }

    public Integer getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(Integer currentYear) {
        this.currentYear = currentYear;
    }

    public BigDecimal getTenthPercentage() {
        return tenthPercentage;
    }

    public void setTenthPercentage(BigDecimal tenthPercentage) {
        this.tenthPercentage = tenthPercentage;
    }

    public BigDecimal getTwelfthPercentage() {
        return twelfthPercentage;
    }

    public void setTwelfthPercentage(BigDecimal twelfthPercentage) {
        this.twelfthPercentage = twelfthPercentage;
    }

    public BigDecimal getCurrentCgpa() {
        return currentCgpa;
    }

    public void setCurrentCgpa(BigDecimal currentCgpa) {
        this.currentCgpa = currentCgpa;
    }

    public String getBacklogSubject() {
        return backlogSubject;
    }

    public void setBacklogSubject(String backlogSubject) {
        this.backlogSubject = backlogSubject;
    }

    public Integer getBacklogSemester() {
        return backlogSemester;
    }

    public void setBacklogSemester(Integer backlogSemester) {
        this.backlogSemester = backlogSemester;
    }

    public Integer getGapYears() {
        return gapYears;
    }

    public void setGapYears(Integer gapYears) {
        this.gapYears = gapYears;
    }

    public String getGapReason() {
        return gapReason;
    }

    public void setGapReason(String gapReason) {
        this.gapReason = gapReason;
    }
}
