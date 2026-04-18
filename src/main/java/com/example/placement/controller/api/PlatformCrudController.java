package com.example.placement.controller.api;

import com.example.placement.dto.student.PlatformLinkRequest;
import com.example.placement.entity.Platform;
import com.example.placement.service.crud.PlatformCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/platforms")
public class PlatformCrudController {

    private final PlatformCrudService platformCrudService;

    public PlatformCrudController(PlatformCrudService platformCrudService) {
        this.platformCrudService = platformCrudService;
    }

    @PostMapping
    public ResponseEntity<Platform> create(
            @RequestParam Long studentProfileId,
            @RequestBody PlatformLinkRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(platformCrudService.create(studentProfileId, request));
    }

    @GetMapping("/{id}")
    public Platform get(@PathVariable Long id) {
        return platformCrudService.get(id);
    }

    @GetMapping
    public List<Platform> list() {
        return platformCrudService.findAll();
    }

    @PutMapping("/{id}")
    public Platform update(@PathVariable Long id, @RequestBody PlatformLinkRequest request) {
        return platformCrudService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        platformCrudService.delete(id);
    }
}
