package com.example.placement.controller.api;

import com.example.placement.dto.ProjectRequest;
import com.example.placement.entity.Project;
import com.example.placement.service.crud.ProjectCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectCrudController {

    private final ProjectCrudService projectCrudService;

    public ProjectCrudController(ProjectCrudService projectCrudService) {
        this.projectCrudService = projectCrudService;
    }

    @PostMapping
    public ResponseEntity<Project> create(
            @RequestParam Long studentProfileId,
            @RequestBody(required = false) ProjectRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectCrudService.create(studentProfileId, request));
    }

    @GetMapping("/{id}")
    public Project get(@PathVariable Long id) {
        return projectCrudService.get(id);
    }

    @GetMapping
    public List<Project> list() {
        return projectCrudService.findAll();
    }

    @PutMapping("/{id}")
    public Project update(@PathVariable Long id, @RequestBody(required = false) ProjectRequest request) {
        return projectCrudService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        projectCrudService.delete(id);
    }
}
