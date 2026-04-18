package com.example.placement.controller.api;

import com.example.placement.dto.student.StudentExperienceCreateRequest;
import com.example.placement.dto.student.StudentExperienceResponse;
import com.example.placement.dto.student.StudentExperienceUpdateRequest;
import com.example.placement.service.crud.StudentExperienceCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-experiences")
public class StudentExperienceCrudController {

    private final StudentExperienceCrudService studentExperienceCrudService;

    public StudentExperienceCrudController(StudentExperienceCrudService studentExperienceCrudService) {
        this.studentExperienceCrudService = studentExperienceCrudService;
    }

    @PostMapping
    public ResponseEntity<StudentExperienceResponse> create(@RequestBody StudentExperienceCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentExperienceCrudService.create(request));
    }

    @GetMapping("/{id}")
    public StudentExperienceResponse get(@PathVariable Long id) {
        return studentExperienceCrudService.get(id);
    }

    @GetMapping
    public List<StudentExperienceResponse> list() {
        return studentExperienceCrudService.findAll();
    }

    @PutMapping("/{id}")
    public StudentExperienceResponse update(@PathVariable Long id, @RequestBody StudentExperienceUpdateRequest request) {
        return studentExperienceCrudService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        studentExperienceCrudService.delete(id);
    }
}
