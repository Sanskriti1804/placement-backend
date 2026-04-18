package com.example.placement.service.crud;

import com.example.placement.dto.staff.StaffManagedRoleRequest;
import com.example.placement.dto.staff.StaffProfileCreateRequest;
import com.example.placement.dto.staff.StaffProfileResponse;
import com.example.placement.dto.staff.StaffProfileUpdateRequest;
import com.example.placement.dto.staff.StaffProfessionalExperienceRequest;
import com.example.placement.entity.StaffManagedRole;
import com.example.placement.entity.StaffProfessionalExperience;
import com.example.placement.entity.main.StaffProfile;
import com.example.placement.entity.main.User;
import com.example.placement.repository.StaffProfileRepo;
import com.example.placement.repository.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffProfileCrudService {

    private final StaffProfileRepo staffProfileRepo;
    private final UserRepo userRepo;

    public StaffProfileCrudService(StaffProfileRepo staffProfileRepo, UserRepo userRepo) {
        this.staffProfileRepo = staffProfileRepo;
        this.userRepo = userRepo;
    }

    private static void replaceManagedRoles(StaffProfile s, List<StaffManagedRoleRequest> reqs) {
        if (reqs == null) {
            return;
        }
        s.getManagedRoles().clear();
        for (StaffManagedRoleRequest r : reqs) {
            StaffManagedRole m = new StaffManagedRole();
            m.setStaff(s);
            if (r.getCompanyIds() != null) {
                m.setCompanyIds(new ArrayList<>(r.getCompanyIds()));
            }
            if (r.getDriveIds() != null) {
                m.setDriveIds(new ArrayList<>(r.getDriveIds()));
            }
            if (r.getStudentIds() != null) {
                m.setStudentIds(new ArrayList<>(r.getStudentIds()));
            }
            if (r.getDepartments() != null) {
                m.setDepartments(new ArrayList<>(r.getDepartments()));
            }
            s.getManagedRoles().add(m);
        }
    }

    private static void replaceProfessionalExperiences(StaffProfile s, List<StaffProfessionalExperienceRequest> reqs) {
        if (reqs == null) {
            return;
        }
        s.getProfessionalExperiences().clear();
        for (StaffProfessionalExperienceRequest r : reqs) {
            if (r.getCompanyName() == null || r.getCompanyName().isBlank()) {
                throw new IllegalArgumentException("Each professional experience requires companyName");
            }
            StaffProfessionalExperience e = new StaffProfessionalExperience();
            e.setStaff(s);
            e.setCompanyName(r.getCompanyName().trim());
            e.setRoleTitle(r.getRoleTitle());
            e.setFromDate(r.getFromDate());
            e.setToDate(r.getToDate());
            e.setDescription(r.getDescription());
            s.getProfessionalExperiences().add(e);
        }
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
        applyScalarsOnly(s, req);
        s = staffProfileRepo.save(s);
        replaceManagedRoles(s, req.getManagedRoles());
        replaceProfessionalExperiences(s, req.getProfessionalExperiences());
        staffProfileRepo.save(s);
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
        if (req.getUserEmail() != null) {
            s.setUserEmail(req.getUserEmail().trim());
        }
        if (req.getPhoneNumber() != null) {
            s.setPhoneNumber(req.getPhoneNumber());
        }
        if (req.getLinkedin() != null) {
            s.setLinkedin(req.getLinkedin());
        }
        if (req.getOfficeLocation() != null) {
            s.setOfficeLocation(req.getOfficeLocation());
        }
        if (req.getCollegeName() != null) {
            s.setCollegeName(req.getCollegeName());
        }
        if (req.getJoiningYear() != null) {
            s.setJoiningYear(req.getJoiningYear());
        }
        if (req.getJoiningMonth() != null) {
            s.setJoiningMonth(req.getJoiningMonth());
        }
        if (req.getEndingYear() != null) {
            s.setEndingYear(req.getEndingYear());
        }
        if (req.getEndingMonth() != null) {
            s.setEndingMonth(req.getEndingMonth());
        }
        if (req.getSubjects() != null) {
            s.setSubjects(new ArrayList<>(req.getSubjects()));
        }
        if (req.getQualifications() != null) {
            s.setQualifications(new ArrayList<>(req.getQualifications()));
        }
        if (req.getManagedRoles() != null) {
            replaceManagedRoles(s, req.getManagedRoles());
        }
        if (req.getProfessionalExperiences() != null) {
            replaceProfessionalExperiences(s, req.getProfessionalExperiences());
        }
        return PlacementDtoMapper.toStaffProfileResponse(reload(staffProfileRepo.save(s).getId()));
    }

    private void applyScalarsOnly(StaffProfile s, StaffProfileCreateRequest req) {
        s.setName(req.getName().trim());
        s.setEmail(req.getEmail().trim());
        s.setUserEmail(req.getUserEmail() != null ? req.getUserEmail().trim() : null);
        s.setPhoneNumber(req.getPhoneNumber());
        s.setLinkedin(req.getLinkedin());
        s.setOfficeLocation(req.getOfficeLocation());
        s.setCollegeName(req.getCollegeName());
        s.setJoiningYear(req.getJoiningYear());
        s.setJoiningMonth(req.getJoiningMonth());
        s.setEndingYear(req.getEndingYear());
        s.setEndingMonth(req.getEndingMonth());
        if (req.getSubjects() != null) {
            s.setSubjects(new ArrayList<>(req.getSubjects()));
        }
        if (req.getQualifications() != null) {
            s.setQualifications(new ArrayList<>(req.getQualifications()));
        }
    }

    private StaffProfile reload(Long id) {
        StaffProfile s = staffProfileRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff profile not found"));
        s.getManagedRoles().size();
        s.getProfessionalExperiences().size();
        s.getSubjects().size();
        s.getQualifications().size();
        return s;
    }

    @Transactional(readOnly = true)
    public StaffProfileResponse get(Long id) {
        return PlacementDtoMapper.toStaffProfileResponse(reload(id));
    }

    @Transactional(readOnly = true)
    public List<StaffProfileResponse> findAll() {
        return staffProfileRepo.findAll().stream()
                .map(sp -> PlacementDtoMapper.toStaffProfileResponse(reload(sp.getId())))
                .toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!staffProfileRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff profile not found");
        }
        staffProfileRepo.deleteById(id);
    }
}
