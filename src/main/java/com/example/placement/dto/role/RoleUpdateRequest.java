package com.example.placement.dto.role;

import com.example.placement.common.enums.RoleType;

public class RoleUpdateRequest {

    private RoleType roleName;

    public RoleType getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleType roleName) {
        this.roleName = roleName;
    }
}
