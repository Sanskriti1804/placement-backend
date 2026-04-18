package com.example.placement.service.crud;

import com.example.placement.dto.RoleResponse;
import com.example.placement.dto.placement.*;
import com.example.placement.entity.*;

import java.util.ArrayList;
import java.util.List;

public final class PlacementDtoMapper {

    private PlacementDtoMapper() {
    }

    public static IndustryResponse toIndustryResponse(Industry e) {
        IndustryResponse r = new IndustryResponse();
        r.setId(e.getId());
        r.setName(e.getName());
        r.setCode(e.getCode());
        r.setDescription(e.getDescription());
        return r;
    }

    public static CompanyResponse toCompanyResponse(Company e) {
        CompanyResponse r = new CompanyResponse();
        r.setId(e.getId());
        r.setName(e.getName());
        r.setTagline(e.getTagline());
        r.setLocation(e.getLocation());
        r.setEmail(e.getEmail());
        r.setWebsiteUrl(e.getWebsiteUrl());
        r.setIndustryId(e.getIndustry() != null ? e.getIndustry().getId() : null);
        r.setDescription(e.getDescription());
        r.setImageUrl(e.getImageUrl());
        return r;
    }

    public static PlacementCoordinatorResponse toCoordinatorResponse(PlacementCoordinator e) {
        PlacementCoordinatorResponse r = new PlacementCoordinatorResponse();
        r.setId(e.getId());
        r.setName(e.getName());
        r.setEmail(e.getEmail());
        r.setInMail(e.getInMail());
        r.setPhoneNumber(e.getPhoneNumber());
        return r;
    }

    public static JobSelectionRoundResponse toRoundResponse(JobSelectionRound e) {
        JobSelectionRoundResponse r = new JobSelectionRoundResponse();
        r.setId(e.getId());
        r.setRoundName(e.getRoundName());
        r.setSequenceOrder(e.getSequenceOrder());
        r.setScheduledDate(e.getScheduledDate());
        return r;
    }

    public static JobResponse toJobResponse(Job e) {
        JobResponse r = new JobResponse();
        r.setId(e.getId());
        r.setCompanyId(e.getCompany() != null ? e.getCompany().getId() : null);
        r.setPlacementCoordinatorId(e.getPlacementCoordinator() != null ? e.getPlacementCoordinator().getId() : null);
        r.setJobType(e.getJobType());
        r.setInternshipDuration(e.getInternshipDuration());
        r.setWorkMode(e.getWorkMode());
        r.setCtcLpa(e.getCtcLpa());
        r.setAdditionalInfo(e.getAdditionalInfo());
        r.setLastDateToApply(e.getLastDateToApply());
        r.setVenue(e.getVenue());
        r.setJobDescription(e.getJobDescription());
        r.setPreparationGuide(e.getPreparationGuide());
        r.setRequirements(e.getRequirements());
        r.setResponsibilities(e.getResponsibilities());
        r.setEligibility(e.getEligibility());
        r.setResultStatus(e.getResultStatus());
        r.setResultDate(e.getResultDate());
        r.setCreatedAt(e.getCreatedAt());
        r.setUpdatedAt(e.getUpdatedAt());
        List<JobSelectionRoundResponse> rounds = new ArrayList<>();
        if (e.getSelectionRounds() != null) {
            for (JobSelectionRound sr : e.getSelectionRounds()) {
                rounds.add(toRoundResponse(sr));
            }
        }
        r.setSelectionRounds(rounds);
        return r;
    }

    public static DriveBranchResponse toDriveBranchResponse(DriveBranch e) {
        DriveBranchResponse r = new DriveBranchResponse();
        r.setId(e.getId());
        r.setBranch(e.getBranch());
        return r;
    }

    public static DriveOfferedRoleResponse toDriveOfferedRoleResponse(DriveOfferedRole e) {
        DriveOfferedRoleResponse r = new DriveOfferedRoleResponse();
        r.setId(e.getId());
        r.setRoleTitle(e.getRoleTitle());
        return r;
    }

    public static DriveResponse toDriveResponse(Drive e) {
        DriveResponse r = new DriveResponse();
        r.setId(e.getId());
        r.setDriveName(e.getDriveName());
        r.setCompanyId(e.getCompany() != null ? e.getCompany().getId() : null);
        r.setRegistrationDeadline(e.getRegistrationDeadline());
        r.setPlacementCoordinatorId(e.getPlacementCoordinator() != null ? e.getPlacementCoordinator().getId() : null);
        r.setCreatedAt(e.getCreatedAt());
        r.setUpdatedAt(e.getUpdatedAt());
        List<DriveBranchResponse> br = new ArrayList<>();
        if (e.getAllowedBranches() != null) {
            for (DriveBranch b : e.getAllowedBranches()) {
                br.add(toDriveBranchResponse(b));
            }
        }
        r.setAllowedBranches(br);
        List<DriveOfferedRoleResponse> or = new ArrayList<>();
        if (e.getOfferedRoles() != null) {
            for (DriveOfferedRole o : e.getOfferedRoles()) {
                or.add(toDriveOfferedRoleResponse(o));
            }
        }
        r.setOfferedRoles(or);
        return r;
    }

    public static DepartmentResponse toDepartmentResponse(Department e) {
        DepartmentResponse r = new DepartmentResponse();
        r.setId(e.getId());
        r.setName(e.getName());
        r.setCode(e.getCode());
        r.setCollegeName(e.getCollegeName());
        return r;
    }

    public static StaffCompanyAssignmentResponse toStaffCompanyAssignmentResponse(StaffCompanyAssignment e) {
        StaffCompanyAssignmentResponse r = new StaffCompanyAssignmentResponse();
        r.setId(e.getId());
        r.setStaffProfileId(e.getStaff() != null ? e.getStaff().getId() : null);
        r.setCompanyId(e.getCompany() != null ? e.getCompany().getId() : null);
        return r;
    }

    public static StaffDriveAssignmentResponse toStaffDriveAssignmentResponse(StaffDriveAssignment e) {
        StaffDriveAssignmentResponse r = new StaffDriveAssignmentResponse();
        r.setId(e.getId());
        r.setStaffProfileId(e.getStaff() != null ? e.getStaff().getId() : null);
        r.setDriveId(e.getDrive() != null ? e.getDrive().getId() : null);
        return r;
    }

    public static StaffStudentAssignmentResponse toStaffStudentAssignmentResponse(StaffStudentAssignment e) {
        StaffStudentAssignmentResponse r = new StaffStudentAssignmentResponse();
        r.setId(e.getId());
        r.setStaffProfileId(e.getStaff() != null ? e.getStaff().getId() : null);
        r.setStudentProfileId(e.getStudent() != null ? e.getStudent().getId() : null);
        return r;
    }

    public static StaffDepartmentAssignmentResponse toStaffDepartmentAssignmentResponse(StaffDepartmentAssignment e) {
        StaffDepartmentAssignmentResponse r = new StaffDepartmentAssignmentResponse();
        r.setId(e.getId());
        r.setStaffProfileId(e.getStaff() != null ? e.getStaff().getId() : null);
        r.setDepartmentId(e.getDepartment() != null ? e.getDepartment().getId() : null);
        return r;
    }

    public static StaffProfileResponse toStaffProfileResponse(StaffProfile e) {
        StaffProfileResponse r = new StaffProfileResponse();
        r.setId(e.getId());
        r.setUserId(e.getUser() != null ? e.getUser().getId() : null);
        r.setName(e.getName());
        r.setEmail(e.getEmail());
        r.setPhoneNumber(e.getPhoneNumber());
        r.setOfficeLocation(e.getOfficeLocation());
        r.setCollegeName(e.getCollegeName());
        r.setFacultyDuty(e.getFacultyDuty());
        r.setPlacementDuty(e.getPlacementDuty());
        r.setCurrentRole(e.getCurrentRole());
        r.setPlacementResponsibilities(e.getPlacementResponsibilities());
        r.setQualification(e.getQualification());
        r.setExperience(e.getExperience());
        r.setSubjectsTaught(e.getSubjectsTaught());
        r.setStartDate(e.getStartDate());
        r.setEndDate(e.getEndDate());
        List<StaffCompanyAssignmentResponse> ca = new ArrayList<>();
        if (e.getCompanyAssignments() != null) {
            for (StaffCompanyAssignment a : e.getCompanyAssignments()) {
                ca.add(toStaffCompanyAssignmentResponse(a));
            }
        }
        r.setCompanyAssignments(ca);
        List<StaffDriveAssignmentResponse> da = new ArrayList<>();
        if (e.getDriveAssignments() != null) {
            for (StaffDriveAssignment a : e.getDriveAssignments()) {
                da.add(toStaffDriveAssignmentResponse(a));
            }
        }
        r.setDriveAssignments(da);
        List<StaffStudentAssignmentResponse> sa = new ArrayList<>();
        if (e.getStudentAssignments() != null) {
            for (StaffStudentAssignment a : e.getStudentAssignments()) {
                sa.add(toStaffStudentAssignmentResponse(a));
            }
        }
        r.setStudentAssignments(sa);
        List<StaffDepartmentAssignmentResponse> depta = new ArrayList<>();
        if (e.getDepartmentAssignments() != null) {
            for (StaffDepartmentAssignment a : e.getDepartmentAssignments()) {
                depta.add(toStaffDepartmentAssignmentResponse(a));
            }
        }
        r.setDepartmentAssignments(depta);
        return r;
    }

    public static RoleResponse toRoleResponse(Role e) {
        RoleResponse r = new RoleResponse();
        r.setId(e.getId());
        r.setRoleName(e.getRoleName());
        return r;
    }
}
