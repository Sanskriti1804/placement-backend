package com.example.placement.config;

import com.example.placement.entity.Role;
import com.example.placement.entity.RoleType;
import com.example.placement.repository.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//runs automatically on startup
@Component      //class - spring managed component - beans
public class DataInitializer implements CommandLineRunner {
    private final RoleRepo roleRepo;

    //constructor based di - spring will provide rolerepo
    public DataInitializer(RoleRepo roleRepo){
        this.roleRepo = roleRepo;
    }

    @Override
    public void run(String... args){
        for (RoleType roleName : RoleType.values()){
            //check if role with this name already exists in the db
            roleRepo.findByRoleName(roleName)
                .orElseGet(() -> roleRepo.save(new Role(null, roleName)));
        }
    }
}
