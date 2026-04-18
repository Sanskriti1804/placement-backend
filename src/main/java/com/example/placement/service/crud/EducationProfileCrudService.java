package com.example.placement.service.crud;

import com.example.placement.dto.student.BackLogRequest;
import com.example.placement.dto.student.EducationProfileRequest;
import com.example.placement.entity.Backlog;
import com.example.placement.entity.Education;
import com.example.placement.entity.main.StudentProfile;
import com.example.placement.repository.BackLogRepo;
import com.example.placement.repository.EducationalProfileRepo;
import com.example.placement.repository.StudentProfileRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EducationProfileCrudService {

    private final EducationalProfileRepo educationRepo;
    private final StudentProfileRepo studentProfileRepo;
    private final BackLogRepo backLogRepo;

    public EducationProfileCrudService(
            EducationalProfileRepo educationRepo,
            StudentProfileRepo studentProfileRepo,
            BackLogRepo backLogRepo
    ) {
        this.educationRepo = educationRepo;
        this.studentProfileRepo = studentProfileRepo;
        this.backLogRepo = backLogRepo;
    }

    @Transactional
    public Education create(EducationProfileRequest req) {
        if (req.getStudentId() == null) {
            throw new IllegalArgumentException("studentId is required");
        }
        if (req.getTenthPercentage() == null || req.getTwelfthPercentage() == null || req.getCurrentCgpa() == null) {
            throw new IllegalArgumentException("tenthPercentage, twelfthPercentage, and currentCgpa are required");
        }
        if (educationRepo.findByStudent_Id(req.getStudentId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Education profile already exists for student");
        }
        StudentProfile student = studentProfileRepo.findById(req.getStudentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student profile not found"));
        Education profile = new Education();
        profile.setStudent(student);
        applyScalars(profile, req);
        if (req.getBacklogs() != null) {
            for (BackLogRequest br : req.getBacklogs()) {
                Backlog b = new Backlog();
                b.setSubject(br.getSubject());
                b.setSemester(br.getSemester());
                b.setEducationProfile(profile);
                profile.getBacklogs().add(b);
            }
        }
        return educationRepo.save(profile);
    }

    @Transactional
    public Education update(Long id, EducationProfileRequest req) {
        Education profile = educationRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Education profile not found"));
        if (req.getUniversity() != null) {
            profile.setUniversity(req.getUniversity());
        }
        if (req.getBranch() != null) {
            profile.setBranch(req.getBranch());
        }
        if (req.getCourse() != null) {
            profile.setCourse(req.getCourse());
        }
        if (req.getDomain() != null) {
            profile.setDomain(req.getDomain());
        }
        if (req.getCurrentYear() != null) {
            profile.setCurrentYear(req.getCurrentYear());
        }
        if (req.getTenthPercentage() != null) {
            profile.setTenthPercentage(req.getTenthPercentage());
        }
        if (req.getTwelfthPercentage() != null) {
            profile.setTwelfthPercentage(req.getTwelfthPercentage());
        }
        if (req.getCurrentCgpa() != null) {
            profile.setCurrentCgpa(req.getCurrentCgpa());
        }
        if (req.getTenthSchoolName() != null) {
            profile.setTenthSchoolName(req.getTenthSchoolName());
        }
        if (req.getTwelfthSchoolName() != null) {
            profile.setTwelfthSchoolName(req.getTwelfthSchoolName());
        }
        if (req.getGraduationCollegeName() != null) {
            profile.setGraduationCollegeName(req.getGraduationCollegeName());
        }
        if (req.getPostGraduationCollegeName() != null) {
            profile.setPostGraduationCollegeName(req.getPostGraduationCollegeName());
        }
        if (req.getGapYears() != null) {
            profile.setGapYears(req.getGapYears());
        }
        if (req.getGapReason() != null) {
            profile.setGapReason(req.getGapReason());
        }
        if (req.getBacklogs() != null) {
            profile.getBacklogs().clear();
            for (BackLogRequest br : req.getBacklogs()) {
                Backlog b = new Backlog();
                b.setSubject(br.getSubject());
                b.setSemester(br.getSemester());
                b.setEducationProfile(profile);
                profile.getBacklogs().add(b);
            }
        }
        Education saved = educationRepo.save(profile);
        saved.setBacklogs(backLogRepo.findByEducationProfile_Id(saved.getId()));
        return saved;
    }

    private void applyScalars(Education profile, EducationProfileRequest req) {
        profile.setUniversity(req.getUniversity());
        profile.setBranch(req.getBranch());
        profile.setCourse(req.getCourse());
        profile.setDomain(req.getDomain());
        profile.setCurrentYear(req.getCurrentYear());
        profile.setTenthPercentage(req.getTenthPercentage());
        profile.setTwelfthPercentage(req.getTwelfthPercentage());
        profile.setCurrentCgpa(req.getCurrentCgpa());
        profile.setTenthSchoolName(req.getTenthSchoolName());
        profile.setTwelfthSchoolName(req.getTwelfthSchoolName());
        profile.setGraduationCollegeName(req.getGraduationCollegeName());
        profile.setPostGraduationCollegeName(req.getPostGraduationCollegeName());
        profile.setGapYears(req.getGapYears());
        profile.setGapReason(req.getGapReason());
    }

    @Transactional(readOnly = true)
    public Education get(Long id) {
        Education profile = educationRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Education profile not found"));
        profile.setBacklogs(backLogRepo.findByEducationProfile_Id(profile.getId()));
        return profile;
    }

    @Transactional(readOnly = true)
    public List<Education> findAll() {
        List<Education> all = educationRepo.findAll();
        for (Education p : all) {
            p.setBacklogs(backLogRepo.findByEducationProfile_Id(p.getId()));
        }
        return all;
    }

    @Transactional
    public void delete(Long id) {
        if (!educationRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Education profile not found");
        }
        educationRepo.deleteById(id);
    }
}
