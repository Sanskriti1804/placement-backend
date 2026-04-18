package com.example.placement.dto.drive;

import com.example.placement.common.enums.BranchType;

public class DriveBranchUpdateRequest {

    private BranchType branch;

    public BranchType getBranch() {
        return branch;
    }

    public void setBranch(BranchType branch) {
        this.branch = branch;
    }
}
