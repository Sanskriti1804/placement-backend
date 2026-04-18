package com.example.placement.controller.api;

import com.example.placement.dto.selection.JobSelectionRoundCreateRequest;
import com.example.placement.dto.selection.SelectionRoundResponse;
import com.example.placement.dto.selection.JobSelectionRoundUpdateRequest;
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
    public ResponseEntity<SelectionRoundResponse> create(
            @RequestParam Long jobId,
            @RequestBody JobSelectionRoundCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobSelectionRoundCrudService.create(jobId, request));
    }

    @GetMapping("/{id}")
    public SelectionRoundResponse get(@PathVariable Long id) {
        return jobSelectionRoundCrudService.get(id);
    }

    @GetMapping
    public List<SelectionRoundResponse> list() {
        return jobSelectionRoundCrudService.findAll();
    }

    @PutMapping("/{id}")
    public SelectionRoundResponse update(@PathVariable Long id, @RequestBody JobSelectionRoundUpdateRequest request) {
        return jobSelectionRoundCrudService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        jobSelectionRoundCrudService.delete(id);
    }
}
