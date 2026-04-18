package com.example.placement.controller.api;

import com.example.placement.dto.placement.StaffStudentAssignmentCreateRequest;
import com.example.placement.dto.placement.StaffStudentAssignmentResponse;
import com.example.placement.dto.placement.StaffStudentAssignmentUpdateRequest;
import com.example.placement.service.crud.StaffStudentAssignmentCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff-student-assignments")
public class StaffStudentAssignmentCrudController {

    private final StaffStudentAssignmentCrudService service;

    public StaffStudentAssignmentCrudController(StaffStudentAssignmentCrudService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StaffStudentAssignmentResponse> create(@RequestBody StaffStudentAssignmentCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public StaffStudentAssignmentResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<StaffStudentAssignmentResponse> list() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    public StaffStudentAssignmentResponse update(@PathVariable Long id, @RequestBody StaffStudentAssignmentUpdateRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
