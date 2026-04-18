package com.example.placement.controller.api;

import com.example.placement.dto.placement.DriveBranchCreateRequest;
import com.example.placement.dto.placement.DriveBranchResponse;
import com.example.placement.dto.placement.DriveBranchUpdateRequest;
import com.example.placement.service.crud.DriveBranchCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drive-branches")
public class DriveBranchCrudController {

    private final DriveBranchCrudService driveBranchCrudService;

    public DriveBranchCrudController(DriveBranchCrudService driveBranchCrudService) {
        this.driveBranchCrudService = driveBranchCrudService;
    }

    @PostMapping
    public ResponseEntity<DriveBranchResponse> create(@RequestBody DriveBranchCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(driveBranchCrudService.create(request));
    }

    @GetMapping("/{id}")
    public DriveBranchResponse get(@PathVariable Long id) {
        return driveBranchCrudService.get(id);
    }

    @GetMapping
    public List<DriveBranchResponse> list() {
        return driveBranchCrudService.findAll();
    }

    @PutMapping("/{id}")
    public DriveBranchResponse update(@PathVariable Long id, @RequestBody DriveBranchUpdateRequest request) {
        return driveBranchCrudService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        driveBranchCrudService.delete(id);
    }
}
