package com.example.placement.controller.api;

import com.example.placement.dto.placement.JobSelectionRoundCreateRequest;
import com.example.placement.dto.placement.JobSelectionRoundResponse;
import com.example.placement.dto.placement.JobSelectionRoundUpdateRequest;
import com.example.placement.service.crud.JobSelectionRoundCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-selection-rounds")
public class JobSelectionRoundCrudController {

    private final JobSelectionRoundCrudService jobSelectionRoundCrudService;

    public JobSelectionRoundCrudController(JobSelectionRoundCrudService jobSelectionRoundCrudService) {
        this.jobSelectionRoundCrudService = jobSelectionRoundCrudService;
    }

    @PostMapping
    public ResponseEntity<JobSelectionRoundResponse> create(
            @RequestParam Long jobId,
            @RequestBody JobSelectionRoundCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobSelectionRoundCrudService.create(jobId, request));
    }

    @GetMapping("/{id}")
    public JobSelectionRoundResponse get(@PathVariable Long id) {
        return jobSelectionRoundCrudService.get(id);
    }

    @GetMapping
    public List<JobSelectionRoundResponse> list() {
        return jobSelectionRoundCrudService.findAll();
    }

    @PutMapping("/{id}")
    public JobSelectionRoundResponse update(@PathVariable Long id, @RequestBody JobSelectionRoundUpdateRequest request) {
        return jobSelectionRoundCrudService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        jobSelectionRoundCrudService.delete(id);
    }
}
