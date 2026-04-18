package com.example.placement.controller.api;

import com.example.placement.dto.placement.DriveOfferedRoleCreateRequest;
import com.example.placement.dto.placement.DriveOfferedRoleResponse;
import com.example.placement.dto.placement.DriveOfferedRoleUpdateRequest;
import com.example.placement.service.crud.DriveOfferedRoleCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drive-offered-roles")
public class DriveOfferedRoleCrudController {

    private final DriveOfferedRoleCrudService driveOfferedRoleCrudService;

    public DriveOfferedRoleCrudController(DriveOfferedRoleCrudService driveOfferedRoleCrudService) {
        this.driveOfferedRoleCrudService = driveOfferedRoleCrudService;
    }

    @PostMapping
    public ResponseEntity<DriveOfferedRoleResponse> create(@RequestBody DriveOfferedRoleCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(driveOfferedRoleCrudService.create(request));
    }

    @GetMapping("/{id}")
    public DriveOfferedRoleResponse get(@PathVariable Long id) {
        return driveOfferedRoleCrudService.get(id);
    }

    @GetMapping
    public List<DriveOfferedRoleResponse> list() {
        return driveOfferedRoleCrudService.findAll();
    }

    @PutMapping("/{id}")
    public DriveOfferedRoleResponse update(@PathVariable Long id, @RequestBody DriveOfferedRoleUpdateRequest request) {
        return driveOfferedRoleCrudService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        driveOfferedRoleCrudService.delete(id);
    }
}
