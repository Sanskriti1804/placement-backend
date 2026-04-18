package com.example.placement.controller.api;

import com.example.placement.dto.BackLogRequest;
import com.example.placement.entity.Backlog;
import com.example.placement.service.crud.BacklogCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/backlogs")
public class BacklogCrudController {

    private final BacklogCrudService backlogCrudService;

    public BacklogCrudController(BacklogCrudService backlogCrudService) {
        this.backlogCrudService = backlogCrudService;
    }

    @PostMapping
    public ResponseEntity<Backlog> create(
            @RequestParam Long educationProfileId,
            @RequestBody(required = false) BackLogRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(backlogCrudService.create(educationProfileId, request));
    }

    @GetMapping("/{id}")
    public Backlog get(@PathVariable Long id) {
        return backlogCrudService.get(id);
    }

    @GetMapping
    public List<Backlog> list() {
        return backlogCrudService.findAll();
    }

    @PutMapping("/{id}")
    public Backlog update(@PathVariable Long id, @RequestBody(required = false) BackLogRequest request) {
        return backlogCrudService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        backlogCrudService.delete(id);
    }
}
