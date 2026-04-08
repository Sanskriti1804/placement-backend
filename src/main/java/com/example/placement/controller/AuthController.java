package com.example.placement.controller;

import com.example.placement.dto.AuthRequest;
import com.example.placement.dto.AuthResponse;
import com.example.placement.dto.RegisterRequest;
import com.example.placement.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     //spring will handle http requests here
@RequestMapping("/api/auth")     //base url for all endpoints in this comtroller
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Backward-compatible registration endpoint for student users.
    @PostMapping("/register/student")
    public AuthResponse registerStudent(@RequestBody RegisterRequest request){
        return authService.registerStudent(request);
    }

    // Backward-compatible login endpoint that delegates to unified auth flow.
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }

    // Single endpoint for login-or-register flow with JWT response.
    @PostMapping("/access")
    public AuthResponse access(@RequestBody AuthRequest request) {
        return authService.authenticateOrRegister(request);
    }
}

