package com.example.placement.dto.application;

import com.example.placement.common.enums.ApplicationStatus;
import com.example.placement.common.enums.InterviewMode;

import java.time.LocalDateTime;

public class JobApplicationCreateRequest {

    private Long studentId;
    private Long jobId;
    private LocalDateTime appliedDate;
    private ApplicationStatus status;
    private LocalDateTime interviewDate;
    private InterviewMode interviewMode;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public LocalDateTime getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(LocalDateTime appliedDate) {
        this.appliedDate = appliedDate;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDateTime getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(LocalDateTime interviewDate) {
        this.interviewDate = interviewDate;
    }

    public InterviewMode getInterviewMode() {
        return interviewMode;
    }

    public void setInterviewMode(InterviewMode interviewMode) {
        this.interviewMode = interviewMode;
    }
}
