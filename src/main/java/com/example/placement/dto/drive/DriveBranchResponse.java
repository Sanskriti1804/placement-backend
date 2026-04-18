package com.example.placement.dto.drive;

import com.example.placement.common.enums.BranchType;

public class DriveBranchResponse {

    private Long id;
    private BranchType branch;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BranchType getBranch() {
        return branch;
    }

    public void setBranch(BranchType branch) {
        this.branch = branch;
    }
}
