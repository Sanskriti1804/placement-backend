package com.example.placement.controller.api;

import com.example.placement.dto.placement.DepartmentCreateRequest;
import com.example.placement.dto.placement.DepartmentResponse;
import com.example.placement.dto.placement.DepartmentUpdateRequest;
import com.example.placement.service.crud.DepartmentCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentCrudController {

    private final DepartmentCrudService departmentCrudService;

    public DepartmentCrudController(DepartmentCrudService departmentCrudService) {
        this.departmentCrudService = departmentCrudService;
    }

    @PostMapping
    public ResponseEntity<DepartmentResponse> create(@RequestBody DepartmentCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentCrudService.create(request));
    }

    @GetMapping("/{id}")
    public DepartmentResponse get(@PathVariable Long id) {
        return departmentCrudService.get(id);
    }

    @GetMapping
    public List<DepartmentResponse> list() {
        return departmentCrudService.findAll();
    }

    @PutMapping("/{id}")
    public DepartmentResponse update(@PathVariable Long id, @RequestBody DepartmentUpdateRequest request) {
        return departmentCrudService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        departmentCrudService.delete(id);
    }
}
