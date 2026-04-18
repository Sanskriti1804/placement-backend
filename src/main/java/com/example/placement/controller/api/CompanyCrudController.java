package com.example.placement.controller.api;

import com.example.placement.dto.company.CompanyCreateRequest;
import com.example.placement.dto.company.CompanyResponse;
import com.example.placement.dto.company.CompanyUpdateRequest;
import com.example.placement.service.crud.CompanyCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyCrudController {

    private final CompanyCrudService companyCrudService;

    public CompanyCrudController(CompanyCrudService companyCrudService) {
        this.companyCrudService = companyCrudService;
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> create(@RequestBody CompanyCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(companyCrudService.create(request));
    }

    @GetMapping("/{id}")
    public CompanyResponse get(@PathVariable Long id) {
        return companyCrudService.get(id);
    }

    @GetMapping
    public List<CompanyResponse> list() {
        return companyCrudService.findAll();
    }

    @PutMapping("/{id}")
    public CompanyResponse update(@PathVariable Long id, @RequestBody CompanyUpdateRequest request) {
        return companyCrudService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        companyCrudService.delete(id);
    }
}
