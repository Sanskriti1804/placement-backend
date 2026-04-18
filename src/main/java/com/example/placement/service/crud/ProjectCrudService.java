package com.example.placement.service.crud;

import com.example.placement.dto.ProjectRequest;
import com.example.placement.entity.Project;
import com.example.placement.entity.StudentProfile;
import com.example.placement.repository.ProjectRepo;
import com.example.placement.repository.StudentProfileRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProjectCrudService {

    private final ProjectRepo projectRepo;
    private final StudentProfileRepo studentProfileRepo;

    public ProjectCrudService(ProjectRepo projectRepo, StudentProfileRepo studentProfileRepo) {
        this.projectRepo = projectRepo;
        this.studentProfileRepo = studentProfileRepo;
    }

    @Transactional
    public Project create(Long studentProfileId, ProjectRequest req) {
        if (studentProfileId == null) {
            throw new IllegalArgumentException("studentProfileId is required");
        }
        StudentProfile student = studentProfileRepo.findById(studentProfileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student profile not found"));
        Project p = new Project();
        p.setStudent(student);
        if (req != null) {
            p.setTitle(req.getTitle());
            p.setDescription(req.getDescription());
            p.setLink(req.getLink());
        }
        return projectRepo.save(p);
    }

    @Transactional
    public Project update(Long id, ProjectRequest req) {
        Project p = projectRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
        if (req != null) {
            p.setTitle(req.getTitle());
            p.setDescription(req.getDescription());
            p.setLink(req.getLink());
        }
        return projectRepo.save(p);
    }

    @Transactional(readOnly = true)
    public Project get(Long id) {
        return projectRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
    }

    @Transactional(readOnly = true)
    public List<Project> findAll() {
        return projectRepo.findAll();
    }

    @Transactional
    public void delete(Long id) {
        if (!projectRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        }
        projectRepo.deleteById(id);
    }
}
