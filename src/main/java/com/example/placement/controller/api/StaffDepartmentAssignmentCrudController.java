package com.example.placement.controller.api;

import com.example.placement.dto.placement.StaffDepartmentAssignmentCreateRequest;
import com.example.placement.dto.placement.StaffDepartmentAssignmentResponse;
import com.example.placement.dto.placement.StaffDepartmentAssignmentUpdateRequest;
import com.example.placement.service.crud.StaffDepartmentAssignmentCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff-department-assignments")
public class StaffDepartmentAssignmentCrudController {

    private final StaffDepartmentAssignmentCrudService service;

    public StaffDepartmentAssignmentCrudController(StaffDepartmentAssignmentCrudService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StaffDepartmentAssignmentResponse> create(@RequestBody StaffDepartmentAssignmentCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public StaffDepartmentAssignmentResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<StaffDepartmentAssignmentResponse> list() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    public StaffDepartmentAssignmentResponse update(@PathVariable Long id, @RequestBody StaffDepartmentAssignmentUpdateRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
