package com.example.placement.controller;

import com.example.placement.dto.BackLogRequest;
import com.example.placement.dto.EducationProfileRequest;
import com.example.placement.entity.Backlog;
import com.example.placement.entity.EducationProfile;
import com.example.placement.service.EducationalProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class EducationProfileController {
    private final EducationalProfileService service;

    public EducationProfileController(EducationalProfileService service) {
        this.service = service;
    }

    @PostMapping("/{studentId}/education")
    public EducationProfile createOrUpdateEducation(
            @PathVariable Long studentId,
            @RequestBody EducationProfileRequest request
    ) {
        EducationProfile profile = new EducationProfile();
        profile.setUniversity(request.getUniversity());
        profile.setBranch(request.getBranch());
        profile.setCourse(request.getCourse());
        profile.setDomain(request.getDomain());
        profile.setCurrentYear(request.getCurrentYear());
        profile.setTenthPercentage(request.getTenthPercentage());
        profile.setTwelfthPercentage(request.getTwelfthPercentage());
        profile.setCurrentCgpa(request.getCurrentCgpa());
        profile.setGapYears(request.getGapYears());
        profile.setGapReason(request.getGapReason());
        if (request.getBacklogs() != null) {
            for (BackLogRequest br : request.getBacklogs()) {
                Backlog b = new Backlog();
                b.setId(br.getId());
                b.setSubject(br.getSubject());
                b.setSemester(br.getSemester());
                b.setEducationProfile(profile);
                profile.getBacklogs().add(b);
            }
        }
        return service.createOrUpdateEducation(profile, studentId);
    }

    @GetMapping("/{studentId}/education")
    public EducationProfile getEducationProfile(@PathVariable Long studentId) {
        return service.getEducationProfile(studentId);
    }


}
