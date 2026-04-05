package com.example.placement.dto;

import com.example.placement.entity.BranchType;
import com.example.placement.entity.CourseType;
import com.example.placement.entity.DomainType;

import java.math.BigDecimal;

public class EducationProfileRequest {
    private Long studentId;
    private String university;
    private BranchType branch;
    private CourseType course;
    private DomainType domain;
    private Integer currentYear;
    private BigDecimal tenthPercentage;
    private BigDecimal twelfthPercentage;
    private BigDecimal currentCgpa;
    private String backlogSubject;
    private Integer backlogSemester;
    private Integer gapYears;
    private String gapReason;

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getUniversity() { return university; }
    public void setUniversity(String university) { this.university = university; }

    public BranchType getBranch() { return branch; }
    public void setBranch(BranchType branch) { this.branch = branch; }

    public CourseType getCourse() { return course; }
    public void setCourse(CourseType course) { this.course = course; }

    public DomainType getDomain() { return domain; }
    public void setDomain(DomainType domain) { this.domain = domain; }

    public Integer getCurrentYear() { return currentYear; }
    public void setCurrentYear(Integer currentYear) { this.currentYear = currentYear; }

    public BigDecimal getTenthPercentage() { return tenthPercentage; }
    public void setTenthPercentage(BigDecimal tenthPercentage) { this.tenthPercentage = tenthPercentage; }

    public BigDecimal getTwelfthPercentage() { return twelfthPercentage; }
    public void setTwelfthPercentage(BigDecimal twelfthPercentage) { this.twelfthPercentage = twelfthPercentage; }

    public BigDecimal getCurrentCgpa() { return currentCgpa; }
    public void setCurrentCgpa(BigDecimal currentCgpa) { this.currentCgpa = currentCgpa; }

    public String getBacklogSubject() { return backlogSubject; }
    public void setBacklogSubject(String backlogSubject) { this.backlogSubject = backlogSubject; }

    public Integer getBacklogSemester() { return backlogSemester; }
    public void setBacklogSemester(Integer backlogSemester) { this.backlogSemester = backlogSemester; }

    public Integer getGapYears() { return gapYears; }
    public void setGapYears(Integer gapYears) { this.gapYears = gapYears; }

    public String getGapReason() { return gapReason; }
    public void setGapReason(String gapReason) { this.gapReason = gapReason; }

}
