package com.example.placement.controller.api;

import com.example.placement.dto.placement.IndustryCreateRequest;
import com.example.placement.dto.placement.IndustryResponse;
import com.example.placement.dto.placement.IndustryUpdateRequest;
import com.example.placement.service.crud.IndustryCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/industries")
public class IndustryCrudController {

    private final IndustryCrudService industryCrudService;

    public IndustryCrudController(IndustryCrudService industryCrudService) {
        this.industryCrudService = industryCrudService;
    }

    @PostMapping
    public ResponseEntity<IndustryResponse> create(@RequestBody IndustryCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(industryCrudService.create(request));
    }

    @GetMapping("/{id}")
    public IndustryResponse get(@PathVariable Long id) {
        return industryCrudService.get(id);
    }

    @GetMapping
    public List<IndustryResponse> list() {
        return industryCrudService.findAll();
    }

    @PutMapping("/{id}")
    public IndustryResponse update(@PathVariable Long id, @RequestBody IndustryUpdateRequest request) {
        return industryCrudService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        industryCrudService.delete(id);
    }
}
