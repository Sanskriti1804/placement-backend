package com.example.placement.service.crud;

import com.example.placement.dto.placement.StaffDepartmentAssignmentCreateRequest;
import com.example.placement.dto.placement.StaffDepartmentAssignmentResponse;
import com.example.placement.dto.placement.StaffDepartmentAssignmentUpdateRequest;
import com.example.placement.entity.Department;
import com.example.placement.entity.StaffDepartmentAssignment;
import com.example.placement.entity.main.StaffProfile;
import com.example.placement.repository.DepartmentRepo;
import com.example.placement.repository.StaffDepartmentAssignmentRepo;
import com.example.placement.repository.StaffProfileRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StaffDepartmentAssignmentCrudService {

    private final StaffDepartmentAssignmentRepo repo;
    private final StaffProfileRepo staffProfileRepo;
    private final DepartmentRepo departmentRepo;

    public StaffDepartmentAssignmentCrudService(
            StaffDepartmentAssignmentRepo repo,
            StaffProfileRepo staffProfileRepo,
            DepartmentRepo departmentRepo
    ) {
        this.repo = repo;
        this.staffProfileRepo = staffProfileRepo;
        this.departmentRepo = departmentRepo;
    }

    @Transactional
    public StaffDepartmentAssignmentResponse create(StaffDepartmentAssignmentCreateRequest req) {
        if (req.getStaffProfileId() == null || req.getDepartmentId() == null) {
            throw new IllegalArgumentException("staffProfileId and departmentId are required");
        }
        StaffProfile staff = staffProfileRepo.findById(req.getStaffProfileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff profile not found"));
        Department department = departmentRepo.findById(req.getDepartmentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));
        StaffDepartmentAssignment e = new StaffDepartmentAssignment();
        e.setStaff(staff);
        e.setDepartment(department);
        return PlacementDtoMapper.toStaffDepartmentAssignmentResponse(repo.save(e));
    }

    @Transactional
    public StaffDepartmentAssignmentResponse update(Long id, StaffDepartmentAssignmentUpdateRequest req) {
        StaffDepartmentAssignment e = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignment not found"));
        if (req.getDepartmentId() != null) {
            Department department = departmentRepo.findById(req.getDepartmentId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));
            e.setDepartment(department);
        }
        return PlacementDtoMapper.toStaffDepartmentAssignmentResponse(repo.save(e));
    }

    @Transactional(readOnly = true)
    public StaffDepartmentAssignmentResponse get(Long id) {
        return repo.findById(id).map(PlacementDtoMapper::toStaffDepartmentAssignmentResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignment not found"));
    }

    @Transactional(readOnly = true)
    public List<StaffDepartmentAssignmentResponse> findAll() {
        return repo.findAll().stream().map(PlacementDtoMapper::toStaffDepartmentAssignmentResponse).toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignment not found");
        }
        repo.deleteById(id);
    }
}
