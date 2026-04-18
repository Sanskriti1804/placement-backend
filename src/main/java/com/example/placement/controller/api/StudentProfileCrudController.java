package com.example.placement.controller.api;

import com.example.placement.dto.StudentProfileRequest;
import com.example.placement.entity.StudentProfile;
import com.example.placement.service.crud.StudentProfileCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-profiles")
public class StudentProfileCrudController {

    private final StudentProfileCrudService studentProfileCrudService;

    public StudentProfileCrudController(StudentProfileCrudService studentProfileCrudService) {
        this.studentProfileCrudService = studentProfileCrudService;
    }

    @PostMapping
    public ResponseEntity<StudentProfile> create(
            @RequestParam Long userId,
            @RequestBody StudentProfileRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentProfileCrudService.create(userId, request));
    }

    @GetMapping("/{id}")
    public StudentProfile get(@PathVariable Long id) {
        return studentProfileCrudService.get(id);
    }

    @GetMapping
    public List<StudentProfile> list() {
        return studentProfileCrudService.findAll();
    }

    @PutMapping("/{id}")
    public StudentProfile update(@PathVariable Long id, @RequestBody StudentProfileRequest request) {
        return studentProfileCrudService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        studentProfileCrudService.delete(id);
    }
}
