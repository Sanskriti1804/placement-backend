package com.example.placement.repository;

import com.example.placement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Repo - Data Access Layer - talks to Db
//<Entity, IDType> → always provide so repo knows what entity & ID type to manage.
//<User, Long> - manager User; whose PK id type - long
public interface UserRepo extends JpaRepository<User, Long> {
    //Custom Query Method - automatically create queries from method names
    //Optional -  returns optional instead of null if user doesnt exist
    Optional<User> findByEmail(String email);
}
