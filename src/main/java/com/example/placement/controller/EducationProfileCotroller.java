package com.example.placement.controller;

import com.example.placement.dto.EducationProfileRequest;
import com.example.placement.entity.EducationProfile;
import com.example.placement.service.EducationalProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class EducationProfileCotroller {
    private final EducationalProfileService service;

    public EducationProfileCotroller(EducationalProfileService service) {
        this.service = service;
    }

    @PostMapping("/{studentId}/education")
    public EducationProfile createOrUpdateEducation(
            //binds a segment of url path to a method parameter
            @PathVariable Long studentId,
            @PathVariable EducationProfileRequest request
            ){
        EducationProfile profile = new EducationProfile();
        profile.setUniversity(request.getUniversity());
        profile.setBranch(request.getBranch());
        profile.setCourse(request.getCourse());
        profile.setDomain(request.getDomain());
        profile.setCurrentYear(request.getCurrentYear());
        profile.setTenthPercentage(request.getTenthPercentage());
        profile.setTwelfthPercentage(request.getTwelfthPercentage());
        profile.setCurrentCgpa(request.getCurrentCgpa());
        profile.setBacklogSubject(request.getBacklogSubject());
        profile.setBacklogSemester(request.getBacklogSemester());
        profile.setGapYears(request.getGapYears());
        profile.setGapReason(request.getGapReason());

        return service.createOrUpdateEducation(profile, studentId);
    }

    @GetMapping("{studentId}/education")
    public EducationProfile getEducationProfile(
            @PathVariable Long studentId
    ){
        return service.getEducationProfile(studentId);
    }
}
