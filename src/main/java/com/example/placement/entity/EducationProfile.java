package com.example.placement.entity;

import com.example.placement.entity.types.BranchType;
import com.example.placement.entity.types.CourseType;
import com.example.placement.entity.types.DomainType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EducationProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "student_id", unique = true)
    @JsonIgnore
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
    @Column(name = "tenth_school_name")
    private String tenthSchoolName;
    @Column(name = "twelfth_school_name")
    private String twelfthSchoolName;
    @Column(name = "graduation_college_name")
    private String graduationCollegeName;
    @Column(name = "post_graduation_college_name")
    private String postGraduationCollegeName;

    private Integer gapYears;
    private String gapReason;

    @OneToMany(mappedBy = "educationProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Backlog> backlogs = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
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

    public String getTenthSchoolName() {
        return tenthSchoolName;
    }

    public void setTenthSchoolName(String tenthSchoolName) {
        this.tenthSchoolName = tenthSchoolName;
    }

    public String getTwelfthSchoolName() {
        return twelfthSchoolName;
    }

    public void setTwelfthSchoolName(String twelfthSchoolName) {
        this.twelfthSchoolName = twelfthSchoolName;
    }

    public String getGraduationCollegeName() {
        return graduationCollegeName;
    }

    public void setGraduationCollegeName(String graduationCollegeName) {
        this.graduationCollegeName = graduationCollegeName;
    }

    public String getPostGraduationCollegeName() {
        return postGraduationCollegeName;
    }

    public void setPostGraduationCollegeName(String postGraduationCollegeName) {
        this.postGraduationCollegeName = postGraduationCollegeName;
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

    public List<Backlog> getBacklogs() {
        return backlogs;
    }

    public void setBacklogs(List<Backlog> backlogs) {
        this.backlogs = backlogs;
    }
}
