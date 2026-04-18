package com.example.placement.service.crud;

import com.example.placement.dto.PlatformLinkRequest;
import com.example.placement.entity.Platform;
import com.example.placement.entity.main.StudentProfile;
import com.example.placement.repository.PlatformRepo;
import com.example.placement.repository.StudentProfileRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PlatformCrudService {

    private final PlatformRepo platformRepo;
    private final StudentProfileRepo studentProfileRepo;

    public PlatformCrudService(PlatformRepo platformRepo, StudentProfileRepo studentProfileRepo) {
        this.platformRepo = platformRepo;
        this.studentProfileRepo = studentProfileRepo;
    }

    @Transactional
    public Platform create(Long studentProfileId, PlatformLinkRequest req) {
        if (studentProfileId == null) {
            throw new IllegalArgumentException("studentProfileId is required");
        }
        if (req == null || req.getType() == null || req.getUrl() == null || req.getUrl().isBlank()) {
            throw new IllegalArgumentException("type and url are required");
        }
        StudentProfile student = studentProfileRepo.findById(studentProfileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student profile not found"));
        Platform p = new Platform();
        p.setStudent(student);
        p.setType(req.getType());
        p.setUrl(req.getUrl().trim());
        return platformRepo.save(p);
    }

    @Transactional
    public Platform update(Long id, PlatformLinkRequest req) {
        Platform p = platformRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Platform not found"));
        if (req != null) {
            if (req.getType() != null) {
                p.setType(req.getType());
            }
            if (req.getUrl() != null && !req.getUrl().isBlank()) {
                p.setUrl(req.getUrl().trim());
            }
        }
        return platformRepo.save(p);
    }

    @Transactional(readOnly = true)
    public Platform get(Long id) {
        return platformRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Platform not found"));
    }

    @Transactional(readOnly = true)
    public List<Platform> findAll() {
        return platformRepo.findAll();
    }

    @Transactional
    public void delete(Long id) {
        if (!platformRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Platform not found");
        }
        platformRepo.deleteById(id);
    }
}
