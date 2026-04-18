package com.example.placement.controller.api;

import com.example.placement.dto.placement.StaffCompanyAssignmentCreateRequest;
import com.example.placement.dto.placement.StaffCompanyAssignmentResponse;
import com.example.placement.dto.placement.StaffCompanyAssignmentUpdateRequest;
import com.example.placement.service.crud.StaffCompanyAssignmentCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff-company-assignments")
public class StaffCompanyAssignmentCrudController {

    private final StaffCompanyAssignmentCrudService service;

    public StaffCompanyAssignmentCrudController(StaffCompanyAssignmentCrudService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StaffCompanyAssignmentResponse> create(@RequestBody StaffCompanyAssignmentCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public StaffCompanyAssignmentResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<StaffCompanyAssignmentResponse> list() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    public StaffCompanyAssignmentResponse update(@PathVariable Long id, @RequestBody StaffCompanyAssignmentUpdateRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
