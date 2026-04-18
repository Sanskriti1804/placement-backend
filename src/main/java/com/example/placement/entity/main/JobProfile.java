package com.example.placement.entity.main;

import com.example.placement.common.entity.SelectionRound;
import com.example.placement.entity.JobApplication;
import com.example.placement.common.enums.WorkMode;
import com.example.placement.common.enums.JobResultStatus;
import com.example.placement.common.enums.JobType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "jobs",
        indexes = {
                @Index(name = "idx_job_company_id", columnList = "company_id"),
                @Index(name = "idx_job_coordinator_staff_id", columnList = "placement_coordinator_staff_id"),
                @Index(name = "idx_job_last_apply", columnList = "last_date_to_apply"),
                @Index(name = "idx_job_type", columnList = "job_type"),
                @Index(name = "idx_job_work_mode", columnList = "work_mode")
        }
)
public class JobProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    @JsonIgnore
    private CompanyProfile company;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_type", nullable = false, length = 32)
    private JobType jobType;

    @Column(name = "internship_duration", length = 128)
    private String internshipDuration;

    @Enumerated(EnumType.STRING)
    @Column(name = "work_mode", nullable = false, length = 32)
    private WorkMode workMode;

    @Column(name = "ctc_lpa", precision = 12, scale = 2)
    private BigDecimal ctcLpa;

    @Column(name = "additional_info", columnDefinition = "TEXT")
    private String additionalInfo;

    @Column(name = "last_date_to_apply")
    private LocalDate lastDateToApply;

    @Column(name = "job_posting_time")
    private LocalDateTime jobPostingTime;

    @Column(length = 512)
    private String venue;

    @Column(name = "job_description", columnDefinition = "TEXT")
    private String jobDescription;

    @Column(name = "preparation_guide", columnDefinition = "TEXT")
    private String preparationGuide;

    @Column(columnDefinition = "TEXT")
    private String requirements;

    @Column(columnDefinition = "TEXT")
    private String responsibilities;

    @Column(columnDefinition = "TEXT")
    private String eligibility;

    @Enumerated(EnumType.STRING)
    @Column(name = "result_status", nullable = false, length = 32)
    private JobResultStatus resultStatus = JobResultStatus.NOT_ANNOUNCED;

    @Column(name = "result_date")
    private LocalDate resultDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "placement_coordinator_staff_id")
    @JsonIgnore
    private StaffProfile placementCoordinator;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SelectionRound> selectionRounds = new ArrayList<>();

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<JobApplication> applications = new ArrayList<>();

    @PrePersist
    void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        if (createdAt == null) {
            createdAt = now;
        }
        updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompanyProfile getCompany() {
        return company;
    }

    public void setCompany(CompanyProfile company) {
        this.company = company;
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

    public StaffProfile getPlacementCoordinator() {
        return placementCoordinator;
    }

    public void setPlacementCoordinator(StaffProfile placementCoordinator) {
        this.placementCoordinator = placementCoordinator;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<SelectionRound> getSelectionRounds() {
        return selectionRounds;
    }

    public void setSelectionRounds(List<SelectionRound> selectionRounds) {
        this.selectionRounds = selectionRounds;
    }

    public List<JobApplication> getApplications() {
        return applications;
    }

    public void setApplications(List<JobApplication> applications) {
        this.applications = applications;
    }
}
