package com.example.placement.service;

import com.example.placement.entity.EducationProfile;
import com.example.placement.entity.StudentProfile;
import com.example.placement.repository.EducationalProfileRepo;
import com.example.placement.repository.StudentProfileRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EducationalProfileService {
    //repo to access educationalProfile data in db
    private final EducationalProfileRepo educationRepo;
    private final StudentProfileRepo studentRepo;

    //constructor based di
    public EducationalProfileService(EducationalProfileRepo educationRepo, StudentProfileRepo studentRepo) {
        this.educationRepo = educationRepo;
        this.studentRepo = studentRepo;
    }

    //create or update educationalProfile for a specific student
    public EducationProfile createOrUpdateEducation(EducationProfile educationProfile, Long studentId){
        //fetch student by id and throws 404 error if not found
        StudentProfile student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        //link the educational profile to the fetchedStudentId
        educationProfile.setStudent(student);

        //checks if an educational profile already exists for the student
        EducationProfile existing = educationRepo.findByStudentId(studentId).orElse(null);
        if (existing != null){
            educationProfile.setId(existing.getId());
        }

        return educationRepo.save(educationProfile);
    }

    public EducationProfile getEducationProfile(Long studentId){
        return educationRepo.findByStudentId(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "education profile no found"));
    }
}




















