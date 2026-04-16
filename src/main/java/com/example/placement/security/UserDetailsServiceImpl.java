package com.example.placement.security;

import com.example.placement.entity.User;
import com.example.placement.repository.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

// Loads application users from database for Spring Security authentication.
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;

    // Constructor-based dependency injection for user repository.
    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    // Loads a user by email and maps roles into Spring Security authorities.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<GrantedAuthority> authorities = user.getRole() == null
                ? Collections.emptyList()
                : List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword() == null ? "" : user.getPassword())
                .authorities(authorities)
                .build();
    }
}
