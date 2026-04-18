package com.example.placement.dto.drive;

public class DriveOfferedRoleCreateRequest {

    private Long driveId;
    private String roleName;
    private Long linkedJobId;

    public Long getDriveId() {
        return driveId;
    }

    public void setDriveId(Long driveId) {
        this.driveId = driveId;
    }

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
