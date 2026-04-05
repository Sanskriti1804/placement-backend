package com.example.placement.service;

import com.example.placement.entity.Backlog;
import com.example.placement.entity.EducationProfile;
import com.example.placement.entity.StudentProfile;
import com.example.placement.repository.BackLogRepo;
import com.example.placement.repository.EducationalProfileRepo;
import com.example.placement.repository.StudentProfileRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class EducationalProfileService {
    private final EducationalProfileRepo educationRepo;
    private final StudentProfileRepo studentRepo;
    private final BackLogRepo backLogRepo;

    public EducationalProfileService(EducationalProfileRepo educationRepo, StudentProfileRepo studentRepo, BackLogRepo backLogRepo) {
        this.educationRepo = educationRepo;
        this.studentRepo = studentRepo;
        this.backLogRepo = backLogRepo;
    }

    public EducationProfile createOrUpdateEducation(EducationProfile educationProfile, Long studentId) {
        //fetch student or throws error
        StudentProfile student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        //check if profile already exists
        EducationProfile existing = educationRepo.findByStudent_Id(studentId).orElse(null);

        //if no existing profile
        if (existing == null) {
            educationProfile.setStudent(student);
            //id - null - so JPA performs insert and not Update
            educationProfile.setId(null);
            //link backlogs to this new profile
            if (educationProfile.getBacklogs() != null) {
                for (Backlog b : educationProfile.getBacklogs()) {
                    b.setEducationProfile(educationProfile);
                }
            }
            //save education profile
            return educationRepo.save(educationProfile);
        }

        existing.setUniversity(educationProfile.getUniversity());
        existing.setBranch(educationProfile.getBranch());
        existing.setCourse(educationProfile.getCourse());
        existing.setDomain(educationProfile.getDomain());
        existing.setCurrentYear(educationProfile.getCurrentYear());
        existing.setTenthPercentage(educationProfile.getTenthPercentage());
        existing.setTwelfthPercentage(educationProfile.getTwelfthPercentage());
        existing.setCurrentCgpa(educationProfile.getCurrentCgpa());
        existing.setGapYears(educationProfile.getGapYears());
        existing.setGapReason(educationProfile.getGapReason());

        // Remove old backlogs → triggers DELETE (orphanRemoval = true)
        existing.getBacklogs().clear();

        // Add new backlogs from request
        if (educationProfile.getBacklogs() != null) {
            for (Backlog b : educationProfile.getBacklogs()) {
                b.setId(null); // force insert (avoid accidental update)
                b.setEducationProfile(existing);
                existing.getBacklogs().add(b);
            }
        }

        // Save everything (cascade handles backlogs)
        return educationRepo.save(existing);
    }

    public EducationProfile getEducationProfile(Long studentId) {
        EducationProfile profile =  educationRepo.findByStudent_Id(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "education profile no found"));
        //manually fetch backlogs and attach - avoids lazy loading issues
        profile.setBacklogs(backLogRepo.findByEducationProfile_Id(profile.getId()));
        return profile;
    }

}














