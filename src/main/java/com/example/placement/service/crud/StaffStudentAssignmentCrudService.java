package com.example.placement.service.crud;

import com.example.placement.dto.placement.StaffStudentAssignmentCreateRequest;
import com.example.placement.dto.placement.StaffStudentAssignmentResponse;
import com.example.placement.dto.placement.StaffStudentAssignmentUpdateRequest;
import com.example.placement.entity.main.StaffProfile;
import com.example.placement.entity.StaffStudentAssignment;
import com.example.placement.entity.main.StudentProfile;
import com.example.placement.repository.StaffProfileRepo;
import com.example.placement.repository.StaffStudentAssignmentRepo;
import com.example.placement.repository.StudentProfileRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StaffStudentAssignmentCrudService {

    private final StaffStudentAssignmentRepo repo;
    private final StaffProfileRepo staffProfileRepo;
    private final StudentProfileRepo studentProfileRepo;

    public StaffStudentAssignmentCrudService(
            StaffStudentAssignmentRepo repo,
            StaffProfileRepo staffProfileRepo,
            StudentProfileRepo studentProfileRepo
    ) {
        this.repo = repo;
        this.staffProfileRepo = staffProfileRepo;
        this.studentProfileRepo = studentProfileRepo;
    }

    @Transactional
    public StaffStudentAssignmentResponse create(StaffStudentAssignmentCreateRequest req) {
        if (req.getStaffProfileId() == null || req.getStudentProfileId() == null) {
            throw new IllegalArgumentException("staffProfileId and studentProfileId are required");
        }
        StaffProfile staff = staffProfileRepo.findById(req.getStaffProfileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff profile not found"));
        StudentProfile student = studentProfileRepo.findById(req.getStudentProfileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student profile not found"));
        StaffStudentAssignment e = new StaffStudentAssignment();
        e.setStaff(staff);
        e.setStudent(student);
        return PlacementDtoMapper.toStaffStudentAssignmentResponse(repo.save(e));
    }

    @Transactional
    public StaffStudentAssignmentResponse update(Long id, StaffStudentAssignmentUpdateRequest req) {
        StaffStudentAssignment e = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignment not found"));
        if (req.getStudentProfileId() != null) {
            StudentProfile student = studentProfileRepo.findById(req.getStudentProfileId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student profile not found"));
            e.setStudent(student);
        }
        return PlacementDtoMapper.toStaffStudentAssignmentResponse(repo.save(e));
    }

    @Transactional(readOnly = true)
    public StaffStudentAssignmentResponse get(Long id) {
        return repo.findById(id).map(PlacementDtoMapper::toStaffStudentAssignmentResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignment not found"));
    }

    @Transactional(readOnly = true)
    public List<StaffStudentAssignmentResponse> findAll() {
        return repo.findAll().stream().map(PlacementDtoMapper::toStaffStudentAssignmentResponse).toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignment not found");
        }
        repo.deleteById(id);
    }
}
