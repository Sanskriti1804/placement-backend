package com.example.placement.service.crud;

import com.example.placement.dto.student.StudentExperienceCreateRequest;
import com.example.placement.dto.student.StudentExperienceResponse;
import com.example.placement.dto.student.StudentExperienceUpdateRequest;
import com.example.placement.entity.StudentExperience;
import com.example.placement.entity.main.StudentProfile;
import com.example.placement.repository.StudentExperienceRepo;
import com.example.placement.repository.StudentProfileRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentExperienceCrudService {

    private final StudentExperienceRepo experienceRepo;
    private final StudentProfileRepo studentProfileRepo;

    public StudentExperienceCrudService(StudentExperienceRepo experienceRepo, StudentProfileRepo studentProfileRepo) {
        this.experienceRepo = experienceRepo;
        this.studentProfileRepo = studentProfileRepo;
    }

    private static StudentExperienceResponse toResponse(StudentExperience e) {
        StudentExperienceResponse r = new StudentExperienceResponse();
        r.setId(e.getId());
        r.setStudentId(e.getStudent() != null ? e.getStudent().getId() : null);
        r.setCompanyName(e.getCompanyName());
        r.setJobType(e.getJobType());
        r.setLocation(e.getLocation());
        r.setFromDate(e.getFromDate());
        r.setToDate(e.getToDate());
        r.setRoleDescription(e.getRoleDescription());
        return r;
    }

    @Transactional
    public StudentExperienceResponse create(StudentExperienceCreateRequest req) {
        if (req.getStudentId() == null || req.getCompanyName() == null || req.getCompanyName().isBlank()
                || req.getJobType() == null) {
            throw new IllegalArgumentException("studentId, companyName, and jobType are required");
        }
        StudentProfile student = studentProfileRepo.findById(req.getStudentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student profile not found"));
        StudentExperience e = new StudentExperience();
        e.setStudent(student);
        e.setCompanyName(req.getCompanyName().trim());
        e.setJobType(req.getJobType());
        e.setLocation(req.getLocation());
        e.setFromDate(req.getFromDate());
        e.setToDate(req.getToDate());
        e.setRoleDescription(req.getRoleDescription());
        return toResponse(experienceRepo.save(e));
    }

    @Transactional
    public StudentExperienceResponse update(Long id, StudentExperienceUpdateRequest req) {
        StudentExperience e = experienceRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Experience not found"));
        if (req.getCompanyName() != null && !req.getCompanyName().isBlank()) {
            e.setCompanyName(req.getCompanyName().trim());
        }
        if (req.getJobType() != null) {
            e.setJobType(req.getJobType());
        }
        if (req.getLocation() != null) {
            e.setLocation(req.getLocation());
        }
        if (req.getFromDate() != null) {
            e.setFromDate(req.getFromDate());
        }
        if (req.getToDate() != null) {
            e.setToDate(req.getToDate());
        }
        if (req.getRoleDescription() != null) {
            e.setRoleDescription(req.getRoleDescription());
        }
        return toResponse(experienceRepo.save(e));
    }

    @Transactional(readOnly = true)
    public StudentExperienceResponse get(Long id) {
        return experienceRepo.findById(id).map(StudentExperienceCrudService::toResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Experience not found"));
    }

    @Transactional(readOnly = true)
    public List<StudentExperienceResponse> findAll() {
        return experienceRepo.findAll().stream().map(StudentExperienceCrudService::toResponse).toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!experienceRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Experience not found");
        }
        experienceRepo.deleteById(id);
    }
}
