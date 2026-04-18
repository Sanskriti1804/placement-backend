package com.example.placement.controller.api;

import com.example.placement.dto.drive.DriveCreateRequest;
import com.example.placement.dto.drive.DriveResponse;
import com.example.placement.dto.selection.SelectionRoundResponse;
import com.example.placement.dto.drive.DriveUpdateRequest;
import com.example.placement.service.crud.DriveCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drives")
public class DriveCrudController {

    private final DriveCrudService driveCrudService;

    public DriveCrudController(DriveCrudService driveCrudService) {
        this.driveCrudService = driveCrudService;
    }

    @PostMapping
    public ResponseEntity<DriveResponse> create(@RequestBody DriveCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(driveCrudService.create(request));
    }

    @GetMapping("/{id}")
    public DriveResponse get(@PathVariable Long id) {
        return driveCrudService.get(id);
    }

    @GetMapping
    public List<DriveResponse> list() {
        return driveCrudService.findAll();
    }

    @GetMapping("/{driveId}/selection-rounds")
    public List<SelectionRoundResponse> listSelectionRounds(@PathVariable Long driveId) {
        return driveCrudService.listSelectionRoundsForDrive(driveId);
    }

    @PutMapping("/{id}")
    public DriveResponse update(@PathVariable Long id, @RequestBody DriveUpdateRequest request) {
        return driveCrudService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        driveCrudService.delete(id);
    }
}
