package com.example.placement.dto;

import com.example.placement.entity.Role;

import java.util.Set;

public class AuthResponse {
    private String token;
    private String email;
    private Set<Role> role;

    public AuthResponse(String token, String email, String password){
        this.token = token;
        this.email = email;
        this.role = role;
    }
    // Getter for token
    public String getToken() {
        return token;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Getter for role
    public  Set<Role> getRole() {
        return role;
    }
}
