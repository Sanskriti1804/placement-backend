package com.example.placement.service.crud;

import com.example.placement.common.entity.SelectionRound;
import com.example.placement.dto.company.CompanyContactSupportResponse;
import com.example.placement.dto.company.CompanyResponse;
import com.example.placement.dto.common.PlacementCoordinatorResponse;
import com.example.placement.dto.department.DepartmentResponse;
import com.example.placement.dto.drive.DriveBranchResponse;
import com.example.placement.dto.drive.DriveOfferedRoleResponse;
import com.example.placement.dto.drive.DriveResponse;
import com.example.placement.dto.job.JobResponse;
import com.example.placement.dto.role.RoleResponse;
import com.example.placement.dto.selection.SelectionRoundResponse;
import com.example.placement.dto.staff.StaffManagedRoleResponse;
import com.example.placement.dto.staff.StaffProfessionalExperienceResponse;
import com.example.placement.dto.staff.StaffProfileResponse;
import com.example.placement.entity.*;
import com.example.placement.entity.main.CompanyProfile;
import com.example.placement.entity.main.DriveProfile;
import com.example.placement.entity.main.JobProfile;
import com.example.placement.entity.main.StaffProfile;
import com.example.placement.common.enums.JobResultStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class PlacementDtoMapper {

    private PlacementDtoMapper() {
    }

    public static CompanyContactSupportResponse toContactSupportResponse(CompanyContactSupport e) {
        CompanyContactSupportResponse r = new CompanyContactSupportResponse();
        r.setId(e.getId());
        r.setName(e.getName());
        r.setEmail(e.getEmail());
        r.setPhone(e.getPhone());
        r.setPreferredMode(e.getPreferredMode());
        return r;
    }

    public static CompanyResponse toCompanyResponse(CompanyProfile e) {
        CompanyResponse r = new CompanyResponse();
        r.setId(e.getId());
        r.setName(e.getName());
        r.setTagline(e.getTagline());
        r.setLocation(e.getLocation());
        r.setEmail(e.getEmail());
        r.setWebsiteUrl(e.getWebsiteUrl());
        r.setDescription(e.getDescription());
        r.setOverview(e.getOverview());
        r.setSector(e.getSector());
        r.setImageUrl(e.getImageUrl());
        r.setDocumentUrls(e.getDocumentUrls() != null ? new ArrayList<>(e.getDocumentUrls()) : new ArrayList<>());
        List<CompanyContactSupportResponse> cs = new ArrayList<>();
        if (e.getContactSupports() != null) {
            for (CompanyContactSupport c : e.getContactSupports()) {
                cs.add(toContactSupportResponse(c));
            }
        }
        r.setContactSupports(cs);
        return r;
    }

    public static PlacementCoordinatorResponse toCoordinatorResponse(StaffProfile e) {
        PlacementCoordinatorResponse r = new PlacementCoordinatorResponse();
        r.setId(e.getId());
        r.setName(e.getName());
        r.setEmail(e.getEmail());
        r.setInMail(e.getLinkedin());
        r.setPhoneNumber(e.getPhoneNumber());
        return r;
    }

    public static SelectionRoundResponse toSelectionRoundResponse(SelectionRound e) {
        SelectionRoundResponse r = new SelectionRoundResponse();
        r.setId(e.getId());
        r.setJobId(e.getJob() != null ? e.getJob().getId() : null);
        r.setDriveId(e.getDrive() != null ? e.getDrive().getId() : null);
        r.setRoundName(e.getRoundName());
        r.setSequenceNumber(e.getSequenceNumber());
        r.setScheduledDate(e.getScheduledDate());
        r.setCompleted(e.isCompleted());
        return r;
    }

    public static JobResponse toJobResponse(JobProfile e) {
        JobResponse r = new JobResponse();
        r.setId(e.getId());
        r.setCompanyId(e.getCompany() != null ? e.getCompany().getId() : null);
        if (e.getCompany() != null) {
            r.setCompany(toCompanyResponse(e.getCompany()));
        }
        r.setPlacementCoordinatorId(e.getPlacementCoordinator() != null ? e.getPlacementCoordinator().getId() : null);
        if (e.getPlacementCoordinator() != null) {
            r.setPlacementCoordinator(toCoordinatorResponse(e.getPlacementCoordinator()));
        }
        r.setJobType(e.getJobType());
        r.setInternshipDuration(e.getInternshipDuration());
        r.setWorkMode(e.getWorkMode());
        r.setCtcLpa(e.getCtcLpa());
        r.setAdditionalInfo(e.getAdditionalInfo());
        r.setLastDateToApply(e.getLastDateToApply());
        r.setJobPostingTime(e.getJobPostingTime());
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
        List<SelectionRoundResponse> rounds = new ArrayList<>();
        if (e.getSelectionRounds() != null) {
            e.getSelectionRounds().stream()
                    .sorted(Comparator.comparing(SelectionRound::getSequenceNumber, Comparator.nullsLast(Integer::compareTo)))
                    .forEach(sr -> rounds.add(toSelectionRoundResponse(sr)));
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
        r.setRoleName(e.getRoleName());
        r.setLinkedJobId(e.getLinkedJob() != null ? e.getLinkedJob().getId() : null);
        return r;
    }

    private static String driveResultDisplay(JobResultStatus status, LocalDate resultDate) {
        if (status == JobResultStatus.NOT_ANNOUNCED) {
            return "Not Announced";
        }
        return resultDate != null ? resultDate.toString() : "";
    }

    public static DriveResponse toDriveResponse(DriveProfile e) {
        DriveResponse r = new DriveResponse();
        r.setId(e.getId());
        r.setDriveName(e.getDriveName());
        r.setCompanyId(e.getCompany() != null ? e.getCompany().getId() : null);
        if (e.getCompany() != null) {
            r.setCompany(toCompanyResponse(e.getCompany()));
        }
        r.setLastDateToApply(e.getLastDateToApply());
        r.setDriveDateTime(e.getDriveDateTime());
        r.setVenue(e.getVenue());
        r.setResultStatus(e.getResultStatus());
        r.setResultDate(e.getResultDate());
        r.setResultDisplay(driveResultDisplay(e.getResultStatus(), e.getResultDate()));
        r.setPlacementCoordinatorId(e.getPlacementCoordinator() != null ? e.getPlacementCoordinator().getId() : null);
        if (e.getPlacementCoordinator() != null) {
            r.setPlacementCoordinator(toCoordinatorResponse(e.getPlacementCoordinator()));
        }
        r.setCreatedAt(e.getCreatedAt());
        r.setUpdatedAt(e.getUpdatedAt());
        List<DriveBranchResponse> br = new ArrayList<>();
        if (e.getAllowedBranches() != null) {
            e.getAllowedBranches().stream()
                    .sorted(Comparator.comparing(b -> b.getBranch().name()))
                    .forEach(b -> br.add(toDriveBranchResponse(b)));
        }
        r.setAllowedBranches(br);
        List<DriveOfferedRoleResponse> or = new ArrayList<>();
        if (e.getOfferedRoles() != null) {
            e.getOfferedRoles().stream()
                    .sorted(Comparator.comparing(DriveOfferedRole::getId, Comparator.nullsLast(Long::compareTo)))
                    .forEach(o -> or.add(toDriveOfferedRoleResponse(o)));
        }
        r.setOfferedRoles(or);
        List<SelectionRoundResponse> rounds = new ArrayList<>();
        if (e.getSelectionRounds() != null) {
            e.getSelectionRounds().stream()
                    .sorted(Comparator.comparing(SelectionRound::getSequenceNumber, Comparator.nullsLast(Integer::compareTo)))
                    .forEach(sr -> rounds.add(toSelectionRoundResponse(sr)));
        }
        r.setSelectionRounds(rounds);
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

    public static StaffManagedRoleResponse toStaffManagedRoleResponse(StaffManagedRole e) {
        StaffManagedRoleResponse r = new StaffManagedRoleResponse();
        r.setId(e.getId());
        r.setCompanyIds(e.getCompanyIds() != null ? new ArrayList<>(e.getCompanyIds()) : new ArrayList<>());
        r.setDriveIds(e.getDriveIds() != null ? new ArrayList<>(e.getDriveIds()) : new ArrayList<>());
        r.setStudentIds(e.getStudentIds() != null ? new ArrayList<>(e.getStudentIds()) : new ArrayList<>());
        r.setDepartments(e.getDepartments() != null ? new ArrayList<>(e.getDepartments()) : new ArrayList<>());
        return r;
    }

    public static StaffProfessionalExperienceResponse toStaffProfessionalExperienceResponse(StaffProfessionalExperience e) {
        StaffProfessionalExperienceResponse r = new StaffProfessionalExperienceResponse();
        r.setId(e.getId());
        r.setCompanyName(e.getCompanyName());
        r.setRoleTitle(e.getRoleTitle());
        r.setFromDate(e.getFromDate());
        r.setToDate(e.getToDate());
        r.setDescription(e.getDescription());
        return r;
    }

    public static StaffProfileResponse toStaffProfileResponse(StaffProfile e) {
        StaffProfileResponse r = new StaffProfileResponse();
        r.setId(e.getId());
        r.setUserId(e.getUser() != null ? e.getUser().getId() : null);
        r.setName(e.getName());
        r.setUserEmail(e.getUserEmail());
        r.setEmail(e.getEmail());
        r.setPhoneNumber(e.getPhoneNumber());
        r.setLinkedin(e.getLinkedin());
        r.setOfficeLocation(e.getOfficeLocation());
        r.setCollegeName(e.getCollegeName());
        r.setJoiningYear(e.getJoiningYear());
        r.setJoiningMonth(e.getJoiningMonth());
        r.setEndingYear(e.getEndingYear());
        r.setEndingMonth(e.getEndingMonth());
        r.setSubjects(e.getSubjects() != null ? new ArrayList<>(e.getSubjects()) : new ArrayList<>());
        r.setQualifications(e.getQualifications() != null ? new ArrayList<>(e.getQualifications()) : new ArrayList<>());
        List<StaffProfessionalExperienceResponse> pe = new ArrayList<>();
        if (e.getProfessionalExperiences() != null) {
            for (StaffProfessionalExperience x : e.getProfessionalExperiences()) {
                pe.add(toStaffProfessionalExperienceResponse(x));
            }
        }
        r.setProfessionalExperiences(pe);
        List<StaffManagedRoleResponse> mr = new ArrayList<>();
        if (e.getManagedRoles() != null) {
            for (StaffManagedRole m : e.getManagedRoles()) {
                mr.add(toStaffManagedRoleResponse(m));
            }
        }
        r.setManagedRoles(mr);
        return r;
    }

    public static RoleResponse toRoleResponse(Role e) {
        RoleResponse r = new RoleResponse();
        r.setId(e.getId());
        r.setRoleName(e.getRoleName());
        return r;
    }
}
