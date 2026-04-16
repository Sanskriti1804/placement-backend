package com.example.placement.dto.placement;

import com.example.placement.entity.BranchType;

public class DriveBranchUpdateRequest {

    private BranchType branch;

    public BranchType getBranch() {
        return branch;
    }

    public void setBranch(BranchType branch) {
        this.branch = branch;
    }
}
