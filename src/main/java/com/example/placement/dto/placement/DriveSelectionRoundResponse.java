package com.example.placement.dto.placement;

import com.example.placement.entity.RoundCompletionStatus;

import java.time.LocalDateTime;

public class DriveSelectionRoundResponse {

    private Long id;
    private Long driveId;
    private String roundName;
    private Integer sequenceOrder;
    private LocalDateTime scheduledDate;
    private RoundCompletionStatus completionStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDriveId() {
        return driveId;
    }

    public void setDriveId(Long driveId) {
        this.driveId = driveId;
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
