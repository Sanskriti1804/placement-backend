package com.example.placement.service;

import com.example.placement.dto.AuthRequest;
import com.example.placement.dto.AuthResponse;
import com.example.placement.dto.RegisterRequest;
import com.example.placement.entity.Role;
import com.example.placement.entity.RoleType;
import com.example.placement.entity.User;
import com.example.placement.repository.RoleRepo;
import com.example.placement.repository.UserRepo;
import com.example.placement.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service        //service bean
public class AuthService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    //constructor based dependency injection
    public AuthService(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // Unified auth flow: registers if user does not exist, otherwise authenticates existing user.
    public AuthResponse authenticateOrRegister(AuthRequest request) {
        if (request == null
                || request.getEmail() == null || request.getEmail().trim().isEmpty()
                || request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required fields");
        }
        String email = request.getEmail().trim().toLowerCase();
        User existing = userRepo.findByEmail(email).orElse(null);

        if (existing == null) {
            RoleType selectedRole = request.getRole();
            if (selectedRole == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role is required for registration");
            }
            validateEmailDomainByRole(email, selectedRole);
            Role role = roleRepo.findByRoleName(selectedRole)
                    .orElseGet(() -> roleRepo.save(new Role(null, selectedRole)));

            User user = new User();
            user.setEmail(email);
            user.setName(deriveNameFromEmail(email));
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.getRoles().add(role);
            User saved = userRepo.save(user);
            return new AuthResponse(jwtUtil.generateToken(saved), saved.getEmail(), extractRoleNames(saved));
        }

        String stored = existing.getPassword();
        if (stored == null || !isBcryptEncoded(stored) || !passwordEncoder.matches(request.getPassword(), stored)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
        return new AuthResponse(jwtUtil.generateToken(existing), existing.getEmail(), extractRoleNames(existing));
    }

    // Backward-compatible registration method that maps to unified auth flow.
    public AuthResponse registerStudent(RegisterRequest request) {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail(request.getEmail());
        authRequest.setPassword(request.getPassword());
        authRequest.setRole(RoleType.STUDENT);
        return authenticateOrRegister(authRequest);
    }

    // Backward-compatible login method mapped to unified auth flow.
    public AuthResponse login(AuthRequest request) {
        return authenticateOrRegister(request);
    }

    // Validates role-based allowed email domains during registration.
    private void validateEmailDomainByRole(String email, RoleType roleType) {
        boolean allowed;
        if (roleType == RoleType.STUDENT) {
            allowed = email.endsWith("@student.com") || email.endsWith("@edu.student.com");
        } else if (roleType == RoleType.STAFF) {
            allowed = email.endsWith("@staff.com") || email.endsWith("@edu.staff.com");
        } else {
            allowed = true;
        }
        if (!allowed) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email domain does not match selected role");
        }
    }

    // Creates a readable default name from email local-part for auto-registration.
    private String deriveNameFromEmail(String email) {
        int idx = email.indexOf('@');
        return idx > 0 ? email.substring(0, idx) : email;
    }

    // Extracts role names for API response and JWT claims readability.
    private List<String> extractRoleNames(User user) {
        if (user.getRoles() == null) {
            return List.of();
        }
        return user.getRoles().stream().map(Role::getRoleName).map(Enum::name).toList();
    }

    // Checks if stored password looks like BCrypt hash.
    private static boolean isBcryptEncoded(String stored) {
        return stored.length() >= 4
                && stored.charAt(0) == '$'
                && (stored.startsWith("$2a$") || stored.startsWith("$2b$") || stored.startsWith("$2y$"));
    }
}
