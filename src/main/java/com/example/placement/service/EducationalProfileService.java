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
        StudentProfile student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        EducationProfile existing = educationRepo.findByStudent_Id(studentId).orElse(null);

        if (existing == null) {
            educationProfile.setStudent(student);
            educationProfile.setId(null);
            //link backlogs to this new profile
            if (educationProfile.getBacklogs() != null) {
                for (Backlog b : educationProfile.getBacklogs()) {
                    b.setEducationProfile(educationProfile);
                }
            }
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

        mergeBacklogs(existing, educationProfile.getBacklogs());
        return educationRepo.save(existing);
    }

    public EducationProfile getEducationProfile(Long studentId) {
        EducationProfile profile =  educationRepo.findByStudent_Id(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "education profile no found"));
        profile.setBacklogs(backLogRepo.findByEducationProfile_Id(profile.getId()));
        return profile;
    }

    private void mergeBacklogs(EducationProfile existingProfile, List<Backlog> newBacklogs) {

        if (newBacklogs == null) {
            return;
        }
        //map existing backlogs by id
        Map<Long, Backlog> existingMap = new HashMap<>();
        for (Backlog b : existingProfile.getBacklogs()){
            if (b.getId() != null){
                existingMap.put(b.getId(), b);
            }
        }
        //save or update new backlogs
        for (Backlog b : newBacklogs) {
            b.setEducationProfile(existingProfile);

            if (b.getId() != null && existingMap.containsKey(b.getId())){
                //update existing backlog
                Backlog existing = existingMap.get(b.getId());
                existing.setSubject(b.getSubject());
                existing.setSemester(b.getSemester());
                backLogRepo.save(existing);
            }
            else {
                backLogRepo.save(b);
                existingProfile.getBacklogs().add(b);

            }
            }
    }
}














