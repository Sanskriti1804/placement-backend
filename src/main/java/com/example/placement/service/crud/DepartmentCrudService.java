package com.example.placement.service.crud;

import com.example.placement.dto.department.DepartmentCreateRequest;
import com.example.placement.dto.department.DepartmentResponse;
import com.example.placement.dto.department.DepartmentUpdateRequest;
import com.example.placement.entity.Department;
import com.example.placement.repository.DepartmentRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DepartmentCrudService {

    private final DepartmentRepo repo;

    public DepartmentCrudService(DepartmentRepo repo) {
        this.repo = repo;
    }

    @Transactional
    public DepartmentResponse create(DepartmentCreateRequest req) {
        if (req.getName() == null || req.getName().isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
        Department e = new Department();
        e.setName(req.getName().trim());
        e.setCode(req.getCode());
        e.setCollegeName(req.getCollegeName());
        return PlacementDtoMapper.toDepartmentResponse(repo.save(e));
    }

    @Transactional
    public DepartmentResponse update(Long id, DepartmentUpdateRequest req) {
        Department e = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));
        if (req.getName() != null && !req.getName().isBlank()) {
            e.setName(req.getName().trim());
        }
        if (req.getCode() != null) {
            e.setCode(req.getCode());
        }
        if (req.getCollegeName() != null) {
            e.setCollegeName(req.getCollegeName());
        }
        return PlacementDtoMapper.toDepartmentResponse(repo.save(e));
    }

    @Transactional(readOnly = true)
    public DepartmentResponse get(Long id) {
        return repo.findById(id).map(PlacementDtoMapper::toDepartmentResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));
    }

    @Transactional(readOnly = true)
    public List<DepartmentResponse> findAll() {
        return repo.findAll().stream().map(PlacementDtoMapper::toDepartmentResponse).toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
        }
        repo.deleteById(id);
    }
}
