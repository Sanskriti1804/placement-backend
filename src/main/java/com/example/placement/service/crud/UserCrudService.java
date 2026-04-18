package com.example.placement.service.crud;

import com.example.placement.dto.RegisterRequest;
import com.example.placement.dto.UserUpdateRequest;
import com.example.placement.entity.User;
import com.example.placement.repository.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserCrudService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserCrudService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User create(RegisterRequest req) {
        if (req.getEmail() == null || req.getEmail().isBlank()
                || req.getPassword() == null || req.getPassword().isBlank()
                || req.getPasswordBased() == null
                || req.getRole() == null) {
            throw new IllegalArgumentException("email, password, passwordBased, and role are required");
        }
        String email = req.getEmail().trim().toLowerCase();
        if (userRepo.findByEmail(email).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
        }
        User u = new User();
        u.setEmail(email);
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        u.setRole(req.getRole());
        return userRepo.save(u);
    }

    @Transactional
    public User update(Long id, UserUpdateRequest req) {
        User u = userRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if (req.getEmail() != null && !req.getEmail().isBlank()) {
            String email = req.getEmail().trim().toLowerCase();
            userRepo.findByEmail(email).ifPresent(other -> {
                if (!other.getId().equals(id)) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
                }
            });
            u.setEmail(email);
        }
        if (req.getPassword() != null && !req.getPassword().isBlank()) {
            u.setPassword(passwordEncoder.encode(req.getPassword()));
        }
        if (req.getRole() != null) {
            u.setRole(req.getRole());
        }
        return userRepo.save(u);
    }

    @Transactional(readOnly = true)
    public User get(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Transactional
    public void delete(Long id) {
        if (!userRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        userRepo.deleteById(id);
    }
}
