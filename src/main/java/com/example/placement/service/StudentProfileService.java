package com.example.placement.service;

import com.example.placement.entity.*;
import com.example.placement.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

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
    public StudentProfile createProfile(StudentProfile studentProfile, Set<String> skillNames, Long userId){
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        studentProfile.setUser(user);

        //ASSIGN SKILLS
        //fetch the skill if exists or create new skill and save it in set
        Set<Skill> skills = skillNames.stream() //stream - converts set into stream - seq of elements you can process functionally
                .map(name -> skillsRepo.findByName(name)
                        .orElseGet(() -> {
                            Skill skill = new Skill();
                            skill.setName(name);      // set only the name
                            return skillsRepo.save(skill);
                        })).collect(java.util.stream.Collectors.toSet());

        studentProfile.setSkills(skills);
        return studentRepo.save(studentProfile);
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
        platform.setStudent(student);
        return platformRepo.save(platform);
    }

    //get profile
    public StudentProfile getStudentProfile(Long studentId){
        return studentRepo.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }
}
