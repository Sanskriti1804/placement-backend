package com.example.placement.service.crud;

import com.example.placement.dto.StudentProfileRequest;
import com.example.placement.entity.Skill;
import com.example.placement.entity.main.StudentProfile;
import com.example.placement.entity.User;
import com.example.placement.repository.SkillsRepo;
import com.example.placement.repository.StudentProfileRepo;
import com.example.placement.repository.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentProfileCrudService {

    private final StudentProfileRepo studentProfileRepo;
    private final UserRepo userRepo;
    private final SkillsRepo skillsRepo;

    public StudentProfileCrudService(StudentProfileRepo studentProfileRepo, UserRepo userRepo, SkillsRepo skillsRepo) {
        this.studentProfileRepo = studentProfileRepo;
        this.userRepo = userRepo;
        this.skillsRepo = skillsRepo;
    }

    @Transactional
    public StudentProfile create(Long userId, StudentProfileRequest req) {
        if (userId == null) {
            throw new IllegalArgumentException("userId is required");
        }
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if (studentProfileRepo.findByUser_Id(userId).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Student profile already exists for user");
        }
        StudentProfile profile = new StudentProfile();
        profile.setUser(user);
        applyRequest(profile, req);
        StudentProfile saved = studentProfileRepo.save(profile);
        if (req.getSkills() != null) {
            Skill skills = skillsRepo.findByStudentId(saved.getId()).orElseGet(Skill::new);
            skills.setStudent(saved);
            skills.setLanguages(req.getSkills().getLanguages());
            skills.setFrameworks(req.getSkills().getFrameworks());
            skills.setTools(req.getSkills().getTools());
            skills.setPlatforms(req.getSkills().getPlatforms());
            skillsRepo.save(skills);
            saved.setSkills(skills);
        }
        return studentProfileRepo.findById(saved.getId()).orElse(saved);
    }

    @Transactional
    public StudentProfile update(Long id, StudentProfileRequest req) {
        StudentProfile profile = studentProfileRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student profile not found"));
        applyRequest(profile, req);
        StudentProfile saved = studentProfileRepo.save(profile);
        if (req.getSkills() != null) {
            Skill skills = skillsRepo.findByStudentId(saved.getId()).orElseGet(Skill::new);
            skills.setStudent(saved);
            skills.setLanguages(req.getSkills().getLanguages());
            skills.setFrameworks(req.getSkills().getFrameworks());
            skills.setTools(req.getSkills().getTools());
            skills.setPlatforms(req.getSkills().getPlatforms());
            skillsRepo.save(skills);
            saved.setSkills(skills);
        }
        return saved;
    }

    private void applyRequest(StudentProfile profile, StudentProfileRequest req) {
        profile.setName(req.getName());
        profile.setDomainRole(req.getDomainRole());
        profile.setPhoneNumber(req.getPhoneNumber());
        profile.setPhotoUrl(req.getPhotoUrl());
        profile.setBio(req.getBio());
        profile.setAddressLine(req.getAddressLine());
        profile.setCity(req.getCity());
        profile.setState(req.getState());
        profile.setDob(req.getDob());
    }

    @Transactional(readOnly = true)
    public StudentProfile get(Long id) {
        StudentProfile p = studentProfileRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student profile not found"));
        if (p.getSkills() != null) {
            p.getSkills().getId();
        }
        return p;
    }

    @Transactional(readOnly = true)
    public List<StudentProfile> findAll() {
        return studentProfileRepo.findAll();
    }

    @Transactional
    public void delete(Long id) {
        if (!studentProfileRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student profile not found");
        }
        studentProfileRepo.deleteById(id);
    }
}
