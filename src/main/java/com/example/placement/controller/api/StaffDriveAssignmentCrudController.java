package com.example.placement.controller.api;

import com.example.placement.dto.placement.StaffDriveAssignmentCreateRequest;
import com.example.placement.dto.placement.StaffDriveAssignmentResponse;
import com.example.placement.dto.placement.StaffDriveAssignmentUpdateRequest;
import com.example.placement.service.crud.StaffDriveAssignmentCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff-drive-assignments")
public class StaffDriveAssignmentCrudController {

    private final StaffDriveAssignmentCrudService service;

    public StaffDriveAssignmentCrudController(StaffDriveAssignmentCrudService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StaffDriveAssignmentResponse> create(@RequestBody StaffDriveAssignmentCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public StaffDriveAssignmentResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<StaffDriveAssignmentResponse> list() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    public StaffDriveAssignmentResponse update(@PathVariable Long id, @RequestBody StaffDriveAssignmentUpdateRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
