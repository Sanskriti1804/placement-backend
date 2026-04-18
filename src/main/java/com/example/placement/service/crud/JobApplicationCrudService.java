package com.example.placement.service.crud;

import com.example.placement.dto.application.JobApplicationCreateRequest;
import com.example.placement.dto.application.JobApplicationResponse;
import com.example.placement.dto.application.JobApplicationUpdateRequest;
import com.example.placement.entity.JobApplication;
import com.example.placement.entity.main.CompanyProfile;
import com.example.placement.entity.main.JobProfile;
import com.example.placement.entity.main.StudentProfile;
import com.example.placement.common.enums.ApplicationStatus;
import com.example.placement.repository.JobApplicationRepo;
import com.example.placement.repository.JobRepo;
import com.example.placement.repository.StudentProfileRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobApplicationCrudService {

    private final JobApplicationRepo applicationRepo;
    private final StudentProfileRepo studentProfileRepo;
    private final JobRepo jobRepo;

    public JobApplicationCrudService(
            JobApplicationRepo applicationRepo,
            StudentProfileRepo studentProfileRepo,
            JobRepo jobRepo
    ) {
        this.applicationRepo = applicationRepo;
        this.studentProfileRepo = studentProfileRepo;
        this.jobRepo = jobRepo;
    }

    private static JobApplicationResponse toResponse(JobApplication e) {
        JobApplicationResponse r = new JobApplicationResponse();
        r.setId(e.getId());
        r.setStudentId(e.getStudent() != null ? e.getStudent().getId() : null);
        r.setJobId(e.getJob() != null ? e.getJob().getId() : null);
        r.setCompanyId(e.getCompany() != null ? e.getCompany().getId() : null);
        r.setAppliedDate(e.getAppliedDate());
        r.setStatus(e.getStatus());
        r.setInterviewDate(e.getInterviewDate());
        r.setInterviewMode(e.getInterviewMode());
        return r;
    }

    @Transactional
    public JobApplicationResponse create(JobApplicationCreateRequest req) {
        if (req.getStudentId() == null || req.getJobId() == null) {
            throw new IllegalArgumentException("studentId and jobId are required");
        }
        if (applicationRepo.findByStudent_IdAndJob_Id(req.getStudentId(), req.getJobId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Application already exists for this student and job");
        }
        StudentProfile student = studentProfileRepo.findById(req.getStudentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student profile not found"));
        JobProfile job = jobRepo.findById(req.getJobId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));
        CompanyProfile company = job.getCompany();
        if (company == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Job has no company");
        }
        JobApplication a = new JobApplication();
        a.setStudent(student);
        a.setJob(job);
        a.setCompany(company);
        a.setAppliedDate(req.getAppliedDate() != null ? req.getAppliedDate() : LocalDateTime.now());
        a.setStatus(req.getStatus() != null ? req.getStatus() : ApplicationStatus.APPLIED);
        a.setInterviewDate(req.getInterviewDate());
        a.setInterviewMode(req.getInterviewMode());
        return toResponse(applicationRepo.save(a));
    }

    @Transactional
    public JobApplicationResponse update(Long id, JobApplicationUpdateRequest req) {
        JobApplication a = applicationRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));
        if (req.getStatus() != null) {
            a.setStatus(req.getStatus());
        }
        if (req.getInterviewDate() != null) {
            a.setInterviewDate(req.getInterviewDate());
        }
        if (req.getInterviewMode() != null) {
            a.setInterviewMode(req.getInterviewMode());
        }
        return toResponse(applicationRepo.save(a));
    }

    @Transactional(readOnly = true)
    public JobApplicationResponse get(Long id) {
        return applicationRepo.findById(id).map(JobApplicationCrudService::toResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));
    }

    @Transactional(readOnly = true)
    public List<JobApplicationResponse> findAll() {
        return applicationRepo.findAll().stream().map(JobApplicationCrudService::toResponse).toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!applicationRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found");
        }
        applicationRepo.deleteById(id);
    }
}
