package com.example.placement.controller.api;

import com.example.placement.dto.SkillRequest;
import com.example.placement.entity.Skill;
import com.example.placement.service.crud.SkillCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillCrudController {

    private final SkillCrudService skillCrudService;

    public SkillCrudController(SkillCrudService skillCrudService) {
        this.skillCrudService = skillCrudService;
    }

    @PostMapping
    public ResponseEntity<Skill> create(
            @RequestParam Long studentProfileId,
            @RequestBody(required = false) SkillRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(skillCrudService.create(studentProfileId, request));
    }

    @GetMapping("/{id}")
    public Skill get(@PathVariable Long id) {
        return skillCrudService.get(id);
    }

    @GetMapping
    public List<Skill> list() {
        return skillCrudService.findAll();
    }

    @PutMapping("/{id}")
    public Skill update(@PathVariable Long id, @RequestBody(required = false) SkillRequest request) {
        return skillCrudService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        skillCrudService.delete(id);
    }
}
