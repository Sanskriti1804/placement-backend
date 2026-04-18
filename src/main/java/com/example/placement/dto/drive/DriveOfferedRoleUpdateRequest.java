package com.example.placement.dto.drive;

public class DriveOfferedRoleUpdateRequest {

    private String roleName;
    private Long linkedJobId;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getLinkedJobId() {
        return linkedJobId;
    }

    public void setLinkedJobId(Long linkedJobId) {
        this.linkedJobId = linkedJobId;
    }
}
