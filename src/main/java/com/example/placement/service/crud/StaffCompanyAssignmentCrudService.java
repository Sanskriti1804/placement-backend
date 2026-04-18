package com.example.placement.service.crud;

import com.example.placement.dto.placement.StaffCompanyAssignmentCreateRequest;
import com.example.placement.dto.placement.StaffCompanyAssignmentResponse;
import com.example.placement.dto.placement.StaffCompanyAssignmentUpdateRequest;
import com.example.placement.entity.Company;
import com.example.placement.entity.StaffCompanyAssignment;
import com.example.placement.entity.StaffProfile;
import com.example.placement.repository.CompanyRepo;
import com.example.placement.repository.StaffCompanyAssignmentRepo;
import com.example.placement.repository.StaffProfileRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StaffCompanyAssignmentCrudService {

    private final StaffCompanyAssignmentRepo repo;
    private final StaffProfileRepo staffProfileRepo;
    private final CompanyRepo companyRepo;

    public StaffCompanyAssignmentCrudService(
            StaffCompanyAssignmentRepo repo,
            StaffProfileRepo staffProfileRepo,
            CompanyRepo companyRepo
    ) {
        this.repo = repo;
        this.staffProfileRepo = staffProfileRepo;
        this.companyRepo = companyRepo;
    }

    @Transactional
    public StaffCompanyAssignmentResponse create(StaffCompanyAssignmentCreateRequest req) {
        if (req.getStaffProfileId() == null || req.getCompanyId() == null) {
            throw new IllegalArgumentException("staffProfileId and companyId are required");
        }
        StaffProfile staff = staffProfileRepo.findById(req.getStaffProfileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff profile not found"));
        Company company = companyRepo.findById(req.getCompanyId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));
        StaffCompanyAssignment e = new StaffCompanyAssignment();
        e.setStaff(staff);
        e.setCompany(company);
        return PlacementDtoMapper.toStaffCompanyAssignmentResponse(repo.save(e));
    }

    @Transactional
    public StaffCompanyAssignmentResponse update(Long id, StaffCompanyAssignmentUpdateRequest req) {
        StaffCompanyAssignment e = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignment not found"));
        if (req.getCompanyId() != null) {
            Company company = companyRepo.findById(req.getCompanyId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));
            e.setCompany(company);
        }
        return PlacementDtoMapper.toStaffCompanyAssignmentResponse(repo.save(e));
    }

    @Transactional(readOnly = true)
    public StaffCompanyAssignmentResponse get(Long id) {
        return repo.findById(id).map(PlacementDtoMapper::toStaffCompanyAssignmentResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignment not found"));
    }

    @Transactional(readOnly = true)
    public List<StaffCompanyAssignmentResponse> findAll() {
        return repo.findAll().stream().map(PlacementDtoMapper::toStaffCompanyAssignmentResponse).toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignment not found");
        }
        repo.deleteById(id);
    }
}
