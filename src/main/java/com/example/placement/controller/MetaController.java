package com.example.placement.controller;

import com.example.placement.entity.BranchType;
import com.example.placement.entity.CourseType;
import com.example.placement.entity.DomainType;
import com.example.placement.service.EducationalProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/meta")
public class MetaController {
    private final EducationalProfileService service;

    public MetaController(EducationalProfileService service) {
        this.service = service;
    }

    // GLOBAL metadata API: used for frontend dropdowns and initial page loading.
    // This response is static/reference data and can be cached safely.
    @GetMapping("/branches")
    public List<BranchType> getAllBranches() {
        return service.getAllBranches();
    }

    // GLOBAL metadata API: returns valid courses for a selected branch (frontend dependent dropdown).
    // This response is reference data and can be cached.
    @GetMapping("/branches/{branch}/courses")
    public List<CourseType> getCourses(@PathVariable BranchType branch) {
        return service.getCoursesForBranch(branch);
    }

    // GLOBAL metadata API: returns all available courses for frontend dropdown initialization.
    // This response is static/reference data and can be cached.
    @GetMapping("/courses")
    public List<CourseType> getAllCourses() {
        return service.getAllCourses();
    }

    // GLOBAL metadata API: returns valid domains for a selected course (frontend dependent dropdown).
    // This response is reference data and can be cached.
    @GetMapping("/courses/{course}/domains")
    public List<DomainType> getDomains(@PathVariable CourseType course) {
        return service.getDomainsForCourse(course);
    }

    // GLOBAL metadata API: optimized single call for frontend initial loading.
    // Contains branches, courses, branch->courses, and course->domains; suitable for caching.
    @GetMapping("/all")
    public Map<String, Object> getAllMetadata() {
        return service.getAllMetadata();
    }
}
