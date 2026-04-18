package com.example.placement.controller.api;

import com.example.placement.dto.placement.DriveSelectionRoundResponse;
import com.example.placement.dto.placement.JobSelectionRoundCreateRequest;
import com.example.placement.dto.placement.JobSelectionRoundUpdateRequest;
import com.example.placement.service.crud.DriveSelectionRoundCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drive-selection-rounds")
public class DriveSelectionRoundCrudController {

    private final DriveSelectionRoundCrudService driveSelectionRoundCrudService;

    public DriveSelectionRoundCrudController(DriveSelectionRoundCrudService driveSelectionRoundCrudService) {
        this.driveSelectionRoundCrudService = driveSelectionRoundCrudService;
    }

    @PostMapping
    public ResponseEntity<DriveSelectionRoundResponse> create(
            @RequestParam Long driveId,
            @RequestBody JobSelectionRoundCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(driveSelectionRoundCrudService.create(driveId, request));
    }

    @GetMapping("/{id}")
    public DriveSelectionRoundResponse get(@PathVariable Long id) {
        return driveSelectionRoundCrudService.get(id);
    }

    @GetMapping
    public List<DriveSelectionRoundResponse> list() {
        return driveSelectionRoundCrudService.findAll();
    }

    @PutMapping("/{id}")
    public DriveSelectionRoundResponse update(@PathVariable Long id, @RequestBody JobSelectionRoundUpdateRequest request) {
        return driveSelectionRoundCrudService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        driveSelectionRoundCrudService.delete(id);
    }
}
