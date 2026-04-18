package com.example.placement.controller;

import com.example.placement.dto.student.BackLogRequest;
import com.example.placement.dto.student.EducationProfileRequest;
import com.example.placement.entity.Backlog;
import com.example.placement.entity.Education;
import com.example.placement.service.EducationalProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class EducationProfileController {
    private final EducationalProfileService service;

    public EducationProfileController(EducationalProfileService service) {
        this.service = service;
    }

    // USER-SPECIFIC API: saves selected branch/course/domain and related education data for one student.
    // Values are expected to come from GLOBAL metadata APIs (e.g., /api/meta/*).
    @PostMapping("/{studentId}/education")
    public Education createOrUpdateEducation(
            @PathVariable Long studentId,
            @RequestBody EducationProfileRequest request
    ) {
        Education profile = new Education();
        profile.setUniversity(request.getUniversity());
        profile.setBranch(request.getBranch());
        profile.setCourse(request.getCourse());
        profile.setDomain(request.getDomain());
        profile.setCurrentYear(request.getCurrentYear());
        profile.setTenthPercentage(request.getTenthPercentage());
        profile.setTwelfthPercentage(request.getTwelfthPercentage());
        profile.setCurrentCgpa(request.getCurrentCgpa());
        profile.setTenthSchoolName(request.getTenthSchoolName());
        profile.setTwelfthSchoolName(request.getTwelfthSchoolName());
        profile.setGraduationCollegeName(request.getGraduationCollegeName());
        profile.setPostGraduationCollegeName(request.getPostGraduationCollegeName());
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

    // USER-SPECIFIC API: fetches one student's education profile using metadata selections.
    @GetMapping("/{studentId}/education")
    public Education getEducationProfile(@PathVariable Long studentId) {
        return service.getEducationProfile(studentId);
    }


}
