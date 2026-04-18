package com.example.placement.controller.api;

import com.example.placement.dto.placement.PlacementCoordinatorCreateRequest;
import com.example.placement.dto.placement.PlacementCoordinatorResponse;
import com.example.placement.dto.placement.PlacementCoordinatorUpdateRequest;
import com.example.placement.service.crud.PlacementCoordinatorCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/placement-coordinators")
public class PlacementCoordinatorCrudController {

    private final PlacementCoordinatorCrudService placementCoordinatorCrudService;

    public PlacementCoordinatorCrudController(PlacementCoordinatorCrudService placementCoordinatorCrudService) {
        this.placementCoordinatorCrudService = placementCoordinatorCrudService;
    }

    @PostMapping
    public ResponseEntity<PlacementCoordinatorResponse> create(@RequestBody PlacementCoordinatorCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(placementCoordinatorCrudService.create(request));
    }

    @GetMapping("/{id}")
    public PlacementCoordinatorResponse get(@PathVariable Long id) {
        return placementCoordinatorCrudService.get(id);
    }

    @GetMapping
    public List<PlacementCoordinatorResponse> list() {
        return placementCoordinatorCrudService.findAll();
    }

    @PutMapping("/{id}")
    public PlacementCoordinatorResponse update(@PathVariable Long id, @RequestBody PlacementCoordinatorUpdateRequest request) {
        return placementCoordinatorCrudService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        placementCoordinatorCrudService.delete(id);
    }
}
