package com.example.placement.dto.placement;

import com.example.placement.entity.BranchType;

public class DriveBranchCreateRequest {

    private Long driveId;
    private BranchType branch;

    public Long getDriveId() {
        return driveId;
    }

    public void setDriveId(Long driveId) {
        this.driveId = driveId;
    }

    public BranchType getBranch() {
        return branch;
    }

    public void setBranch(BranchType branch) {
        this.branch = branch;
    }
}
