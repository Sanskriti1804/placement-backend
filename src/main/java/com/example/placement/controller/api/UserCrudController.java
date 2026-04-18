package com.example.placement.controller.api;

import com.example.placement.dto.auth.RegisterRequest;
import com.example.placement.dto.user.UserUpdateRequest;
import com.example.placement.entity.main.User;
import com.example.placement.service.crud.UserCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserCrudController {

    private final UserCrudService userCrudService;

    public UserCrudController(UserCrudService userCrudService) {
        this.userCrudService = userCrudService;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userCrudService.create(request));
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return userCrudService.get(id);
    }

    @GetMapping
    public List<User> list() {
        return userCrudService.findAll();
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        return userCrudService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userCrudService.delete(id);
    }
}
