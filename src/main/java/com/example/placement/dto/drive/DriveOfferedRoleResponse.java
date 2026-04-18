package com.example.placement.dto.drive;

public class DriveOfferedRoleResponse {

    private Long id;
    private String roleName;
    private Long linkedJobId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
