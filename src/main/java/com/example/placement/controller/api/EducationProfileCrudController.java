package com.example.placement.controller.api;

import com.example.placement.dto.EducationProfileRequest;
import com.example.placement.entity.EducationProfile;
import com.example.placement.service.crud.EducationProfileCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education-profiles")
public class EducationProfileCrudController {

    private final EducationProfileCrudService educationProfileCrudService;

    public EducationProfileCrudController(EducationProfileCrudService educationProfileCrudService) {
        this.educationProfileCrudService = educationProfileCrudService;
    }

    @PostMapping
    public ResponseEntity<EducationProfile> create(@RequestBody EducationProfileRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(educationProfileCrudService.create(request));
    }

    @GetMapping("/{id}")
    public EducationProfile get(@PathVariable Long id) {
        return educationProfileCrudService.get(id);
    }

    @GetMapping
    public List<EducationProfile> list() {
        return educationProfileCrudService.findAll();
    }

    @PutMapping("/{id}")
    public EducationProfile update(@PathVariable Long id, @RequestBody EducationProfileRequest request) {
        return educationProfileCrudService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        educationProfileCrudService.delete(id);
    }
}
