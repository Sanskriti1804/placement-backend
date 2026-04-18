package com.example.placement.dto.role;

import com.example.placement.common.enums.RoleType;

public class RoleResponse {

    private Long id;
    private RoleType roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleType getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleType roleName) {
        this.roleName = roleName;
    }
}
