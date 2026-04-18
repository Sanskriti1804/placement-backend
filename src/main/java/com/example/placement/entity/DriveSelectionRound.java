package com.example.placement.entity;

import com.example.placement.entity.main.DriveProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "drive_selection_rounds",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_drive_round_sequence",
                columnNames = {"drive_id", "sequence_order"}
        ),
        indexes = {
                @Index(name = "idx_dsr_drive_id", columnList = "drive_id"),
                @Index(name = "idx_dsr_scheduled", columnList = "scheduled_date")
        }
)
public class DriveSelectionRound {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "drive_id", nullable = false)
    @JsonIgnore
    private DriveProfile drive;

    @Column(name = "round_name", nullable = false, length = 255)
    private String roundName;

    @Column(name = "sequence_order", nullable = false)
    private Integer sequenceOrder;

    @Column(name = "scheduled_date")
    private LocalDateTime scheduledDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "completion_status", nullable = false, length = 32)
    private RoundCompletionStatus completionStatus = RoundCompletionStatus.PENDING;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getSequenceOrder() {
        return sequenceOrder;
    }

    public void setSequenceOrder(Integer sequenceOrder) {
        this.sequenceOrder = sequenceOrder;
    }

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDateTime scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public RoundCompletionStatus getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(RoundCompletionStatus completionStatus) {
        this.completionStatus = completionStatus;
    }
}
