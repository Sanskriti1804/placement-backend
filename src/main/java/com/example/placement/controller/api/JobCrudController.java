package com.example.placement.controller.api;

import com.example.placement.dto.placement.JobCreateRequest;
import com.example.placement.dto.placement.JobResponse;
import com.example.placement.dto.placement.JobUpdateRequest;
import com.example.placement.service.crud.JobCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobCrudController {

    private final JobCrudService jobCrudService;

    public JobCrudController(JobCrudService jobCrudService) {
        this.jobCrudService = jobCrudService;
    }

    @PostMapping
    public ResponseEntity<JobResponse> create(@RequestBody JobCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobCrudService.create(request));
    }

    @GetMapping("/{id}")
    public JobResponse get(@PathVariable Long id) {
        return jobCrudService.get(id);
    }

    @GetMapping
    public List<JobResponse> list() {
        return jobCrudService.findAll();
    }

    @PutMapping("/{id}")
    public JobResponse update(@PathVariable Long id, @RequestBody JobUpdateRequest request) {
        return jobCrudService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        jobCrudService.delete(id);
    }
}
