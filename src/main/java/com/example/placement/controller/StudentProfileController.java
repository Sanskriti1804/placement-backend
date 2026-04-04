package com.example.placement.controller;

import com.example.placement.dto.PlatformLinkRequest;
import com.example.placement.dto.ProjectRequest;
import com.example.placement.dto.StudentProfileRequest;
import com.example.placement.dto.StudentProfileResponse;
import com.example.placement.entity.Platform;
import com.example.placement.entity.Project;
import com.example.placement.entity.StudentProfile;
import com.example.placement.service.StudentProfileService;
import org.springframework.web.bind.annotation.*;

@RestController     //spring will handle http requests here
@RequestMapping("/api/students")
public class StudentProfileController {
    private final StudentProfileService service;


    public StudentProfileController(StudentProfileService service) {
        this.service = service;
    }

    //http post request
    @PostMapping("/{userId}/profile")
    public StudentProfile createProfile(
            //binds a segment of url path to a method parameter
            @PathVariable Long userId,
            //binds the http reuest body to a java object
            @RequestBody StudentProfileRequest request
    ){
        StudentProfile profile = new StudentProfile();
        profile.setRollNo(request.getRollNo());
        profile.setPhotoUrl(request.getPhotoUrl());
        profile.setBio(request.getBio());

        //call service to save profile
        StudentProfile savedProfile =  service.createProfile(profile, request.getSkills(), userId);

        return savedProfile;
    }

    //add proj
    @PostMapping("/{studentId}/projects")
    public Project addProject(
            @PathVariable Long studentId,
            @RequestBody ProjectRequest request
    ){
        Project project = new Project();
        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setLink(request.getLink());

        return service.addProject(project, studentId);
    }

    @PostMapping("/{studentId}/platforms")
    public Platform addPlatform(
            @PathVariable Long studentId,
            @RequestBody PlatformLinkRequest request
    ) {
        Platform platform = new Platform();
        platform.setType(request.getType());
        platform.setUrl(request.getUrl());

        return service.addLink(platform, studentId);
    }

    @GetMapping("/{studentId}/profile")
    public StudentProfile getProfile(@PathVariable Long studentId){
        return service.getStudentProfile(studentId);
    }
}
