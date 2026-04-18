package com.example.placement.dto.auth;

import com.example.placement.common.enums.RoleType;

//DTO - data transfer object - carries registeration data from the client to the backend
public class RegisterRequest {

    //fields that the client sends when registering a user
    private String email;
    private String password;
    private Boolean passwordBased;
    private RoleType role;

    //getters and setters
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

    public Boolean getPasswordBased() {
        return passwordBased;
    }

    public void setPasswordBased(Boolean passwordBased) {
        this.passwordBased = passwordBased;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}
