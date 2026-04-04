package com.example.placement.controller;

import com.example.placement.dto.LoginRequest;
import com.example.placement.dto.RegisterRequest;
import com.example.placement.entity.User;
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

    //http post request
    @PostMapping("/register/student")
    //maps the incoming json request body to the the register request object
    public User registerStudent(@RequestBody RegisterRequest request){
        //calls authservice mthod - and sav user entity as json repsonse
        return authService.registerStudent(request);
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}

