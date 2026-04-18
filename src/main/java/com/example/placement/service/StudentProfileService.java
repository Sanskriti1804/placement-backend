package com.example.placement.service;

import com.example.placement.entity.*;
import com.example.placement.entity.types.PlatformType;
import com.example.placement.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.EnumSet;
import java.util.List;

@Service
public class StudentProfileService {
    private final StudentProfileRepo studentRepo;
    private final SkillsRepo skillsRepo;
    private final ProjectRepo projectRepo;
    private final PlatformRepo platformRepo;
    private final UserRepo userRepo;

    public StudentProfileService(StudentProfileRepo studentRepo, SkillsRepo skillsRepo, ProjectRepo projectRepo, PlatformRepo platformRepo, UserRepo userRepo) {
        this.studentRepo = studentRepo;
        this.skillsRepo = skillsRepo;
        this.projectRepo = projectRepo;
        this.platformRepo = platformRepo;
        this.userRepo = userRepo;
    }

    //create profile
    public StudentProfile createProfile(StudentProfile studentProfile, Skill skillInput, Long userId){
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        studentProfile.setUser(user);
        StudentProfile savedProfile = studentRepo.save(studentProfile);

        Skill skills = skillsRepo.findByStudentId(savedProfile.getId()).orElseGet(Skill::new);
        skills.setStudent(savedProfile);
        if (skillInput != null) {
            skills.setLanguages(skillInput.getLanguages());
            skills.setFrameworks(skillInput.getFrameworks());
            skills.setTools(skillInput.getTools());
            skills.setPlatforms(skillInput.getPlatforms());
        }
        skillsRepo.save(skills);
        savedProfile.setSkills(skills);
        return savedProfile;
    }

    //add projects
    public Project addProject(Project project, Long studentId){
        StudentProfile student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
        project.setStudent(student);
        return projectRepo.save(project);
    }

    public Platform addLink(Platform platform, Long studentId){
        StudentProfile student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
        if (platform.getType() == null || platform.getUrl() == null || platform.getUrl().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Platform type and url are required");
        }
        platform.setUrl(platform.getUrl().trim());
        platform.setStudent(student);
        Platform saved = platformRepo.findByStudentIdAndType(studentId, platform.getType())
                .map(existing -> {
                    existing.setUrl(platform.getUrl().trim());
                    return platformRepo.save(existing);
                })
                .orElseGet(() -> platformRepo.save(platform));
        return saved;
    }

    //get profile
    public StudentProfile getStudentProfile(Long studentId){
        StudentProfile profile = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
        validateRequiredPlatformLinks(studentId);
        return profile;
    }

    private void validateRequiredPlatformLinks(Long studentId) {
        List<Platform> links = platformRepo.findByStudentId(studentId);
        EnumSet<PlatformType> present = EnumSet.noneOf(PlatformType.class);
        for (Platform link : links) {
            if (link.getType() != null && link.getUrl() != null && !link.getUrl().trim().isEmpty()) {
                present.add(link.getType());
            }
        }
        if (!(present.contains(PlatformType.GITHUB)
                && present.contains(PlatformType.LINKEDIN)
                && present.contains(PlatformType.RESUME))) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Required platform links missing: GITHUB, LINKEDIN and RESUME"
            );
        }
    }
}
