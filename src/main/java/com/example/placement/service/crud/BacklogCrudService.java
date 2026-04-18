package com.example.placement.service.crud;

import com.example.placement.dto.BackLogRequest;
import com.example.placement.entity.Backlog;
import com.example.placement.entity.EducationProfile;
import com.example.placement.repository.BackLogRepo;
import com.example.placement.repository.EducationalProfileRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BacklogCrudService {

    private final BackLogRepo backLogRepo;
    private final EducationalProfileRepo educationRepo;

    public BacklogCrudService(BackLogRepo backLogRepo, EducationalProfileRepo educationRepo) {
        this.backLogRepo = backLogRepo;
        this.educationRepo = educationRepo;
    }

    @Transactional
    public Backlog create(Long educationProfileId, BackLogRequest req) {
        if (educationProfileId == null) {
            throw new IllegalArgumentException("educationProfileId is required");
        }
        EducationProfile profile = educationRepo.findById(educationProfileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Education profile not found"));
        Backlog b = new Backlog();
        b.setEducationProfile(profile);
        if (req != null) {
            b.setSubject(req.getSubject());
            b.setSemester(req.getSemester());
        }
        return backLogRepo.save(b);
    }

    @Transactional
    public Backlog update(Long id, BackLogRequest req) {
        Backlog b = backLogRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Backlog not found"));
        if (req != null) {
            if (req.getSubject() != null) {
                b.setSubject(req.getSubject());
            }
            if (req.getSemester() != null) {
                b.setSemester(req.getSemester());
            }
        }
        return backLogRepo.save(b);
    }

    @Transactional(readOnly = true)
    public Backlog get(Long id) {
        return backLogRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Backlog not found"));
    }

    @Transactional(readOnly = true)
    public List<Backlog> findAll() {
        return backLogRepo.findAll();
    }

    @Transactional
    public void delete(Long id) {
        if (!backLogRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Backlog not found");
        }
        backLogRepo.deleteById(id);
    }
}
