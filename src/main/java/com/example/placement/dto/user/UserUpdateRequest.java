package com.example.placement.dto.user;

import com.example.placement.common.enums.RoleType;

public class UserUpdateRequest {

    private String email;
    private String password;
    private RoleType role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}
