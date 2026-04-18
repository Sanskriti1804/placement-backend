package com.example.placement.controller.api;

import com.example.placement.dto.application.JobApplicationCreateRequest;
import com.example.placement.dto.application.JobApplicationResponse;
import com.example.placement.dto.application.JobApplicationUpdateRequest;
import com.example.placement.service.crud.JobApplicationCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationCrudController {

    private final JobApplicationCrudService jobApplicationCrudService;

    public JobApplicationCrudController(JobApplicationCrudService jobApplicationCrudService) {
        this.jobApplicationCrudService = jobApplicationCrudService;
    }

    @PostMapping
    public ResponseEntity<JobApplicationResponse> create(@RequestBody JobApplicationCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobApplicationCrudService.create(request));
    }

    @GetMapping("/{id}")
    public JobApplicationResponse get(@PathVariable Long id) {
        return jobApplicationCrudService.get(id);
    }

    @GetMapping
    public List<JobApplicationResponse> list() {
        return jobApplicationCrudService.findAll();
    }

    @PutMapping("/{id}")
    public JobApplicationResponse update(@PathVariable Long id, @RequestBody JobApplicationUpdateRequest request) {
        return jobApplicationCrudService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        jobApplicationCrudService.delete(id);
    }
}
