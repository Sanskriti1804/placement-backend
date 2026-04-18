package com.example.placement.dto.placement;

import com.example.placement.entity.types.JobResultStatus;
import com.example.placement.entity.types.JobType;
import com.example.placement.entity.WorkMode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JobCreateRequest {

    private Long companyId;
    private Long placementCoordinatorId;
    private JobType jobType;
    private String internshipDuration;
    private WorkMode workMode;
    private BigDecimal ctcLpa;
    private String additionalInfo;
    private LocalDate lastDateToApply;
    private LocalDateTime jobPostingTime;
    private String venue;
    private String jobDescription;
    private String preparationGuide;
    private String requirements;
    private String responsibilities;
    private String eligibility;
    private JobResultStatus resultStatus;
    private LocalDate resultDate;
    private List<JobSelectionRoundCreateRequest> selectionRounds = new ArrayList<>();

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getPlacementCoordinatorId() {
        return placementCoordinatorId;
    }

    public void setPlacementCoordinatorId(Long placementCoordinatorId) {
        this.placementCoordinatorId = placementCoordinatorId;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public String getInternshipDuration() {
        return internshipDuration;
    }

    public void setInternshipDuration(String internshipDuration) {
        this.internshipDuration = internshipDuration;
    }

    public WorkMode getWorkMode() {
        return workMode;
    }

    public void setWorkMode(WorkMode workMode) {
        this.workMode = workMode;
    }

    public BigDecimal getCtcLpa() {
        return ctcLpa;
    }

    public void setCtcLpa(BigDecimal ctcLpa) {
        this.ctcLpa = ctcLpa;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public LocalDate getLastDateToApply() {
        return lastDateToApply;
    }

    public void setLastDateToApply(LocalDate lastDateToApply) {
        this.lastDateToApply = lastDateToApply;
    }

    public LocalDateTime getJobPostingTime() {
        return jobPostingTime;
    }

    public void setJobPostingTime(LocalDateTime jobPostingTime) {
        this.jobPostingTime = jobPostingTime;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getPreparationGuide() {
        return preparationGuide;
    }

    public void setPreparationGuide(String preparationGuide) {
        this.preparationGuide = preparationGuide;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public JobResultStatus getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(JobResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }

    public LocalDate getResultDate() {
        return resultDate;
    }

    public void setResultDate(LocalDate resultDate) {
        this.resultDate = resultDate;
    }

    public List<JobSelectionRoundCreateRequest> getSelectionRounds() {
        return selectionRounds;
    }

    public void setSelectionRounds(List<JobSelectionRoundCreateRequest> selectionRounds) {
        this.selectionRounds = selectionRounds;
    }
}
