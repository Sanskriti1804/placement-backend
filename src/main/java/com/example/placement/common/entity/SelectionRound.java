package com.example.placement.common.entity;

import com.example.placement.entity.main.DriveProfile;
import com.example.placement.entity.main.JobProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "selection_rounds",
        indexes = {
                @Index(name = "idx_sel_round_job", columnList = "job_id"),
                @Index(name = "idx_sel_round_drive", columnList = "drive_id"),
                @Index(name = "idx_sel_round_scheduled", columnList = "scheduled_date")
        }
)
@Check(constraints = "(job_id IS NOT NULL AND drive_id IS NULL) OR (job_id IS NULL AND drive_id IS NOT NULL)")
public class SelectionRound {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    @JsonIgnore
    private JobProfile job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drive_id")
    @JsonIgnore
    private DriveProfile drive;

    @Column(name = "round_name", nullable = false, length = 255)
    private String roundName;

    @Column(name = "sequence_number", nullable = false)
    private Integer sequenceNumber;

    @Column(name = "scheduled_date")
    private LocalDateTime scheduledDate;

    @Column(name = "is_completed", nullable = false)
    private boolean completed;

    @PrePersist
    @PreUpdate
    void refreshCompletedFromSchedule() {
        if (scheduledDate == null) {
            completed = false;
            return;
        }
        completed = !scheduledDate.isAfter(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobProfile getJob() {
        return job;
    }

    public void setJob(JobProfile job) {
        this.job = job;
    }

    public DriveProfile getDrive() {
        return drive;
    }

    public void setDrive(DriveProfile drive) {
        this.drive = drive;
    }

    public String getRoundName() {
        return roundName;
    }

    public void setRoundName(String roundName) {
        this.roundName = roundName;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDateTime scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
