package com.example.placement.dto;

import com.example.placement.entity.RoleType;

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
