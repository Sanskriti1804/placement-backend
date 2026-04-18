package com.example.placement.service.crud;

import com.example.placement.dto.SkillRequest;
import com.example.placement.entity.Skill;
import com.example.placement.entity.main.StudentProfile;
import com.example.placement.repository.SkillsRepo;
import com.example.placement.repository.StudentProfileRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SkillCrudService {

    private final SkillsRepo skillsRepo;
    private final StudentProfileRepo studentProfileRepo;

    public SkillCrudService(SkillsRepo skillsRepo, StudentProfileRepo studentProfileRepo) {
        this.skillsRepo = skillsRepo;
        this.studentProfileRepo = studentProfileRepo;
    }

    @Transactional
    public Skill create(Long studentProfileId, SkillRequest req) {
        if (studentProfileId == null) {
            throw new IllegalArgumentException("studentProfileId is required");
        }
        StudentProfile student = studentProfileRepo.findById(studentProfileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student profile not found"));
        Skill skills = skillsRepo.findByStudentId(studentProfileId).orElseGet(Skill::new);
        skills.setStudent(student);
        if (req != null) {
            skills.setLanguages(req.getLanguages());
            skills.setFrameworks(req.getFrameworks());
            skills.setTools(req.getTools());
            skills.setPlatforms(req.getPlatforms());
        }
        return skillsRepo.save(skills);
    }

    @Transactional
    public Skill update(Long id, SkillRequest req) {
        Skill skills = skillsRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found"));
        if (req != null) {
            skills.setLanguages(req.getLanguages());
            skills.setFrameworks(req.getFrameworks());
            skills.setTools(req.getTools());
            skills.setPlatforms(req.getPlatforms());
        }
        return skillsRepo.save(skills);
    }

    @Transactional(readOnly = true)
    public Skill get(Long id) {
        return skillsRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found"));
    }

    @Transactional(readOnly = true)
    public List<Skill> findAll() {
        return skillsRepo.findAll();
    }

    @Transactional
    public void delete(Long id) {
        if (!skillsRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found");
        }
        skillsRepo.deleteById(id);
    }
}
