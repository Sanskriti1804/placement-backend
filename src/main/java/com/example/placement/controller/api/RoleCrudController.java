package com.example.placement.controller.api;

import com.example.placement.dto.RoleCreateRequest;
import com.example.placement.dto.RoleResponse;
import com.example.placement.dto.RoleUpdateRequest;
import com.example.placement.service.crud.RoleCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleCrudController {

    private final RoleCrudService roleCrudService;

    public RoleCrudController(RoleCrudService roleCrudService) {
        this.roleCrudService = roleCrudService;
    }

    @PostMapping
    public ResponseEntity<RoleResponse> create(@RequestBody RoleCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleCrudService.create(request));
    }

    @GetMapping("/{id}")
    public RoleResponse get(@PathVariable Long id) {
        return roleCrudService.get(id);
    }

    @GetMapping
    public List<RoleResponse> list() {
        return roleCrudService.findAll();
    }

    @PutMapping("/{id}")
    public RoleResponse update(@PathVariable Long id, @RequestBody RoleUpdateRequest request) {
        return roleCrudService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        roleCrudService.delete(id);
    }
}
