package com.example.placement.dto.auth;

import java.util.List;

// DTO returned after successful authentication with JWT token.
public class AuthResponse {
    private String token;
    private String email;
    private List<String> roles;

    public AuthResponse(String token, String email, List<String> roles){
        this.token = token;
        this.email = email;
        this.roles = roles;
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
    public List<String> getRoles() {
        return roles;
    }
}
