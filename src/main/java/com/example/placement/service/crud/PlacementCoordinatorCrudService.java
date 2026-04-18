package com.example.placement.service.crud;

import com.example.placement.dto.placement.PlacementCoordinatorCreateRequest;
import com.example.placement.dto.placement.PlacementCoordinatorResponse;
import com.example.placement.dto.placement.PlacementCoordinatorUpdateRequest;
import com.example.placement.entity.PlacementCoordinator;
import com.example.placement.repository.PlacementCoordinatorRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PlacementCoordinatorCrudService {

    private final PlacementCoordinatorRepo repo;

    public PlacementCoordinatorCrudService(PlacementCoordinatorRepo repo) {
        this.repo = repo;
    }

    @Transactional
    public PlacementCoordinatorResponse create(PlacementCoordinatorCreateRequest req) {
        if (req.getName() == null || req.getName().isBlank()
                || req.getEmail() == null || req.getEmail().isBlank()) {
            throw new IllegalArgumentException("name and email are required");
        }
        PlacementCoordinator e = new PlacementCoordinator();
        e.setName(req.getName().trim());
        e.setEmail(req.getEmail().trim());
        e.setInMail(req.getInMail());
        e.setPhoneNumber(req.getPhoneNumber());
        return PlacementDtoMapper.toCoordinatorResponse(repo.save(e));
    }

    @Transactional
    public PlacementCoordinatorResponse update(Long id, PlacementCoordinatorUpdateRequest req) {
        PlacementCoordinator e = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Coordinator not found"));
        if (req.getName() != null && !req.getName().isBlank()) {
            e.setName(req.getName().trim());
        }
        if (req.getEmail() != null && !req.getEmail().isBlank()) {
            e.setEmail(req.getEmail().trim());
        }
        if (req.getInMail() != null) {
            e.setInMail(req.getInMail());
        }
        if (req.getPhoneNumber() != null) {
            e.setPhoneNumber(req.getPhoneNumber());
        }
        return PlacementDtoMapper.toCoordinatorResponse(repo.save(e));
    }

    @Transactional(readOnly = true)
    public PlacementCoordinatorResponse get(Long id) {
        return repo.findById(id).map(PlacementDtoMapper::toCoordinatorResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Coordinator not found"));
    }

    @Transactional(readOnly = true)
    public List<PlacementCoordinatorResponse> findAll() {
        return repo.findAll().stream().map(PlacementDtoMapper::toCoordinatorResponse).toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Coordinator not found");
        }
        repo.deleteById(id);
    }
}
