package com.example.placement.service.crud;

import com.example.placement.dto.placement.StaffProfileCreateRequest;
import com.example.placement.dto.placement.StaffProfileResponse;
import com.example.placement.dto.placement.StaffProfileUpdateRequest;
import com.example.placement.entity.*;
import com.example.placement.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StaffProfileCrudService {

    private final StaffProfileRepo staffProfileRepo;
    private final UserRepo userRepo;
    private final CompanyRepo companyRepo;
    private final DriveRepo driveRepo;
    private final StudentProfileRepo studentProfileRepo;
    private final DepartmentRepo departmentRepo;

    public StaffProfileCrudService(
            StaffProfileRepo staffProfileRepo,
            UserRepo userRepo,
            CompanyRepo companyRepo,
            DriveRepo driveRepo,
            StudentProfileRepo studentProfileRepo,
            DepartmentRepo departmentRepo
    ) {
        this.staffProfileRepo = staffProfileRepo;
        this.userRepo = userRepo;
        this.companyRepo = companyRepo;
        this.driveRepo = driveRepo;
        this.studentProfileRepo = studentProfileRepo;
        this.departmentRepo = departmentRepo;
    }

    @Transactional
    public StaffProfileResponse create(StaffProfileCreateRequest req) {
        if (req.getUserId() == null) {
            throw new IllegalArgumentException("userId is required");
        }
        if (req.getName() == null || req.getName().isBlank()
                || req.getEmail() == null || req.getEmail().isBlank()) {
            throw new IllegalArgumentException("name and email are required");
        }
        User user = userRepo.findById(req.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if (staffProfileRepo.findByUser_Id(req.getUserId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Staff profile already exists for user");
        }
        StaffProfile s = new StaffProfile();
        s.setUser(user);
        applyCreateScalars(s, req);
        s = staffProfileRepo.save(s);
        replaceAssignments(s, req.getAssignedCompanyIds(), req.getAssignedDriveIds(),
                req.getAssignedStudentProfileIds(), req.getAssignedDepartmentIds());
        return PlacementDtoMapper.toStaffProfileResponse(reload(s.getId()));
    }

    @Transactional
    public StaffProfileResponse update(Long id, StaffProfileUpdateRequest req) {
        StaffProfile s = staffProfileRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff profile not found"));
        if (req.getName() != null && !req.getName().isBlank()) {
            s.setName(req.getName().trim());
        }
        if (req.getEmail() != null && !req.getEmail().isBlank()) {
            s.setEmail(req.getEmail().trim());
        }
        if (req.getPhoneNumber() != null) {
            s.setPhoneNumber(req.getPhoneNumber());
        }
        if (req.getOfficeLocation() != null) {
            s.setOfficeLocation(req.getOfficeLocation());
        }
        if (req.getCollegeName() != null) {
            s.setCollegeName(req.getCollegeName());
        }
        if (req.getFacultyDuty() != null) {
            s.setFacultyDuty(req.getFacultyDuty());
        }
        if (req.getPlacementDuty() != null) {
            s.setPlacementDuty(req.getPlacementDuty());
        }
        if (req.getCurrentRole() != null) {
            s.setCurrentRole(req.getCurrentRole());
        }
        if (req.getPlacementResponsibilities() != null) {
            s.setPlacementResponsibilities(req.getPlacementResponsibilities());
        }
        if (req.getQualification() != null) {
            s.setQualification(req.getQualification());
        }
        if (req.getExperience() != null) {
            s.setExperience(req.getExperience());
        }
        if (req.getSubjectsTaught() != null) {
            s.setSubjectsTaught(req.getSubjectsTaught());
        }
        if (req.getStartDate() != null) {
            s.setStartDate(req.getStartDate());
        }
        if (req.getEndDate() != null) {
            s.setEndDate(req.getEndDate());
        }
        return PlacementDtoMapper.toStaffProfileResponse(reload(staffProfileRepo.save(s).getId()));
    }

    private void applyCreateScalars(StaffProfile s, StaffProfileCreateRequest req) {
        s.setName(req.getName().trim());
        s.setEmail(req.getEmail().trim());
        s.setPhoneNumber(req.getPhoneNumber());
        s.setOfficeLocation(req.getOfficeLocation());
        s.setCollegeName(req.getCollegeName());
        s.setFacultyDuty(req.getFacultyDuty());
        s.setPlacementDuty(req.getPlacementDuty());
        s.setCurrentRole(req.getCurrentRole());
        s.setPlacementResponsibilities(req.getPlacementResponsibilities());
        s.setQualification(req.getQualification());
        s.setExperience(req.getExperience());
        s.setSubjectsTaught(req.getSubjectsTaught());
        s.setStartDate(req.getStartDate());
        s.setEndDate(req.getEndDate());
    }

    private void replaceAssignments(
            StaffProfile s,
            List<Long> companyIds,
            List<Long> driveIds,
            List<Long> studentIds,
            List<Long> departmentIds
    ) {
        if (companyIds != null) {
            s.getCompanyAssignments().clear();
            for (Long cid : companyIds) {
                Company c = companyRepo.findById(cid)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));
                StaffCompanyAssignment a = new StaffCompanyAssignment();
                a.setStaff(s);
                a.setCompany(c);
                s.getCompanyAssignments().add(a);
            }
        }
        if (driveIds != null) {
            s.getDriveAssignments().clear();
            for (Long did : driveIds) {
                Drive d = driveRepo.findById(did)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Drive not found"));
                StaffDriveAssignment a = new StaffDriveAssignment();
                a.setStaff(s);
                a.setDrive(d);
                s.getDriveAssignments().add(a);
            }
        }
        if (studentIds != null) {
            s.getStudentAssignments().clear();
            for (Long sid : studentIds) {
                StudentProfile st = studentProfileRepo.findById(sid)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student profile not found"));
                StaffStudentAssignment a = new StaffStudentAssignment();
                a.setStaff(s);
                a.setStudent(st);
                s.getStudentAssignments().add(a);
            }
        }
        if (departmentIds != null) {
            s.getDepartmentAssignments().clear();
            for (Long depId : departmentIds) {
                Department d = departmentRepo.findById(depId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));
                StaffDepartmentAssignment a = new StaffDepartmentAssignment();
                a.setStaff(s);
                a.setDepartment(d);
                s.getDepartmentAssignments().add(a);
            }
        }
    }

    private StaffProfile reload(Long id) {
        StaffProfile s = staffProfileRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff profile not found"));
        s.getCompanyAssignments().size();
        s.getDriveAssignments().size();
        s.getStudentAssignments().size();
        s.getDepartmentAssignments().size();
        return s;
    }

    @Transactional(readOnly = true)
    public StaffProfileResponse get(Long id) {
        return PlacementDtoMapper.toStaffProfileResponse(reload(id));
    }

    @Transactional(readOnly = true)
    public List<StaffProfileResponse> findAll() {
        return staffProfileRepo.findAll().stream().map(sp -> PlacementDtoMapper.toStaffProfileResponse(reload(sp.getId()))).toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!staffProfileRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff profile not found");
        }
        staffProfileRepo.deleteById(id);
    }
}
