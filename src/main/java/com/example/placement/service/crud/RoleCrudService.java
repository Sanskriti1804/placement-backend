package com.example.placement.service.crud;

import com.example.placement.dto.role.RoleCreateRequest;
import com.example.placement.dto.role.RoleResponse;
import com.example.placement.dto.role.RoleUpdateRequest;
import com.example.placement.entity.Role;
import com.example.placement.repository.RoleRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RoleCrudService {

    private final RoleRepo roleRepo;

    public RoleCrudService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Transactional
    public RoleResponse create(RoleCreateRequest req) {
        if (req.getRoleName() == null) {
            throw new IllegalArgumentException("roleName is required");
        }
        Role e = new Role();
        e.setRoleName(req.getRoleName());
        return PlacementDtoMapper.toRoleResponse(roleRepo.save(e));
    }

    @Transactional
    public RoleResponse update(Long id, RoleUpdateRequest req) {
        Role e = roleRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
        if (req.getRoleName() != null) {
            e.setRoleName(req.getRoleName());
        }
        return PlacementDtoMapper.toRoleResponse(roleRepo.save(e));
    }

    @Transactional(readOnly = true)
    public RoleResponse get(Long id) {
        return roleRepo.findById(id).map(PlacementDtoMapper::toRoleResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
    }

    @Transactional(readOnly = true)
    public List<RoleResponse> findAll() {
        return roleRepo.findAll().stream().map(PlacementDtoMapper::toRoleResponse).toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!roleRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found");
        }
        roleRepo.deleteById(id);
    }
}
