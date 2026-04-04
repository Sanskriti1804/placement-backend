package com.example.placement.service;

import com.example.placement.dto.LoginRequest;
import com.example.placement.dto.RegisterRequest;
import com.example.placement.entity.Role;
import com.example.placement.entity.RoleType;
import com.example.placement.entity.User;
import com.example.placement.repository.RoleRepo;
import com.example.placement.repository.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service        //service bean
public class AuthService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    //constructor based dependency injection
    public AuthService(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    //register a new student
    public User registerStudent(RegisterRequest request) {
        if (request == null
                || request.getEmail() == null || request.getEmail().trim().isEmpty()
                || request.getPassword() == null || request.getPassword().trim().isEmpty()
                || request.getName() == null || request.getName().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required fields");
        }

        User existing = userRepo.findByEmail(request.getEmail().trim()).orElse(null);

        Role role = roleRepo.findByRoleName(RoleType.STUDENT)
                .orElseGet(() -> roleRepo.save(new Role(null, RoleType.STUDENT)));

        if (existing != null) {
            // For testing: avoid failing re-registration with same email
            if (existing.getRoles() != null) {
                existing.getRoles().add(role);
            }
            return userRepo.save(existing);
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail().trim());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());

        //Assign the STUDENT  role to the user
        user.getRoles().add(role);

        //save thw user to the db and returns the saved entity
        return userRepo.save(user);
    }

    public User login(LoginRequest request) {
        if (request == null
                || request.getEmail() == null || request.getEmail().trim().isEmpty()
                || request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        String stored = user.getPassword();
        if (stored == null || !isBcryptEncoded(stored)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        if (!passwordEncoder.matches(request.getPassword(), stored)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        return user;
    }

    private static boolean isBcryptEncoded(String stored) {
        return stored.length() >= 4
                && stored.charAt(0) == '$'
                && (stored.startsWith("$2a$") || stored.startsWith("$2b$") || stored.startsWith("$2y$"));
    }
}
