package com.example.placement.dto.application;

import com.example.placement.common.enums.ApplicationStatus;
import com.example.placement.common.enums.InterviewMode;

import java.time.LocalDateTime;

public class JobApplicationUpdateRequest {

    private ApplicationStatus status;
    private LocalDateTime interviewDate;
    private InterviewMode interviewMode;

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
