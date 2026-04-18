package com.example.placement.controller.api;

import com.example.placement.dto.staff.StaffProfileCreateRequest;
import com.example.placement.dto.staff.StaffProfileResponse;
import com.example.placement.dto.staff.StaffProfileUpdateRequest;
import com.example.placement.service.crud.StaffProfileCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff-profiles")
public class StaffProfileCrudController {

    private final StaffProfileCrudService staffProfileCrudService;

    public StaffProfileCrudController(StaffProfileCrudService staffProfileCrudService) {
        this.staffProfileCrudService = staffProfileCrudService;
    }

    @PostMapping
    public ResponseEntity<StaffProfileResponse> create(@RequestBody StaffProfileCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(staffProfileCrudService.create(request));
    }

    @GetMapping("/{id}")
    public StaffProfileResponse get(@PathVariable Long id) {
        return staffProfileCrudService.get(id);
    }

    @GetMapping
    public List<StaffProfileResponse> list() {
        return staffProfileCrudService.findAll();
    }

    @PutMapping("/{id}")
    public StaffProfileResponse update(@PathVariable Long id, @RequestBody StaffProfileUpdateRequest request) {
        return staffProfileCrudService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        staffProfileCrudService.delete(id);
    }
}
