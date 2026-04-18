package com.example.placement.dto;

import com.example.placement.entity.RoleType;

public class RoleCreateRequest {

    private RoleType roleName;

    public RoleType getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleType roleName) {
        this.roleName = roleName;
    }
}
