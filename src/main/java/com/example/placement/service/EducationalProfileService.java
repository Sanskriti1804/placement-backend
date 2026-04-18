package com.example.placement.service;

import com.example.placement.config.EducationMapping;
import com.example.placement.entity.Backlog;
import com.example.placement.common.enums.BranchType;
import com.example.placement.common.enums.CourseType;
import com.example.placement.common.enums.DomainType;
import com.example.placement.entity.Education;
import com.example.placement.entity.main.StudentProfile;
import com.example.placement.repository.BackLogRepo;
import com.example.placement.repository.EducationalProfileRepo;
import com.example.placement.repository.StudentProfileRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    public Education createOrUpdateEducation(Education educationProfile, Long studentId) {
        //fetch student or throws error
        StudentProfile student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        //check if profile already exists
        Education existing = educationRepo.findByStudent_Id(studentId).orElse(null);

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
        existing.setTenthSchoolName(educationProfile.getTenthSchoolName());
        existing.setTwelfthSchoolName(educationProfile.getTwelfthSchoolName());
        existing.setGraduationCollegeName(educationProfile.getGraduationCollegeName());
        existing.setPostGraduationCollegeName(educationProfile.getPostGraduationCollegeName());
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

    public Education getEducationProfile(Long studentId) {
        Education profile =  educationRepo.findByStudent_Id(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "education profile no found"));
        //manually fetch backlogs and attach - avoids lazy loading issues
        profile.setBacklogs(backLogRepo.findByEducationProfile_Id(profile.getId()));
        return profile;
    }

    public List<BranchType> getAllBranches() {
        return Arrays.asList(BranchType.values());
    }

    public List<CourseType> getCoursesForBranch(BranchType branch) {
        return EducationMapping.getCoursesForBranch(branch);
    }

    public List<CourseType> getAllCourses() {
        return Arrays.asList(CourseType.values());
    }

    public List<DomainType> getDomainsForCourse(CourseType course) {
        return EducationMapping.getDomainForCourse(course);
    }

    public Map<String, Object> getAllMetadata() {
        List<BranchType> branches = getAllBranches();
        List<CourseType> courses = getAllCourses();

        Map<BranchType, List<CourseType>> branchToCourses = new LinkedHashMap<>();
        for (BranchType branch : branches) {
            branchToCourses.put(branch, getCoursesForBranch(branch));
        }

        Map<CourseType, List<DomainType>> courseToDomains = new LinkedHashMap<>();
        for (CourseType course : courses) {
            courseToDomains.put(course, getDomainsForCourse(course));
        }

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("branches", branches);
        result.put("courses", courses);
        result.put("branchToCourses", branchToCourses);
        result.put("courseToDomains", courseToDomains);
        return result;
    }

}














