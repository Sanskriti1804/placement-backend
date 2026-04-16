package com.example.placement.config;

import com.example.placement.entity.Role;
import com.example.placement.entity.RoleType;
import com.example.placement.repository.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//runs automatically on startup
public class DataInitializer implements CommandLineRunner {
    private final RoleRepo roleRepo;

    //constructor based di - spring will provide rolerepo
    public DataInitializer(RoleRepo roleRepo){
        this.roleRepo = roleRepo;
    }

    @Override
    public void run(String... args){
        // No-op: auth now uses users.role directly (single enum role on users table).
        // Keeping this class without @Component prevents legacy roles-table seeding.
    }
}
