package com.example.placement.repository;

import com.example.placement.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Repo - Data Access Layer - talks to Db
//<Entity, IDType> → always provide so repo knows what entity & ID type to manage.
public interface StudentProfileRepo extends JpaRepository<StudentProfile, Long> {
    //Optional -  returns optional instead of null if user doesnt exist
    Optional<StudentProfile> findByUserId(Long userId);
}
