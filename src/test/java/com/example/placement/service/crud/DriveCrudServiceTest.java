package com.example.placement.service.crud;

import com.example.placement.dto.drive.DriveCreateRequest;
import com.example.placement.common.enums.BranchType;
import com.example.placement.common.enums.JobResultStatus;
import com.example.placement.entity.main.CompanyProfile;
import com.example.placement.entity.main.StaffProfile;
import com.example.placement.repository.CompanyRepo;
import com.example.placement.repository.DriveRepo;
import com.example.placement.repository.SelectionRoundRepo;
import com.example.placement.repository.StaffProfileRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DriveCrudServiceTest {

    @Mock
    private DriveRepo driveRepo;
    @Mock
    private CompanyRepo companyRepo;
    @Mock
    private StaffProfileRepo staffProfileRepo;
    @Mock
    private SelectionRoundRepo selectionRoundRepo;

    @InjectMocks
    private DriveCrudService driveCrudService;

    private DriveCreateRequest baseRequest() {
        DriveCreateRequest r = new DriveCreateRequest();
        r.setDriveName("Winter placements");
        r.setCompanyId(1L);
        r.setLastDateToApply(LocalDateTime.now().plusDays(14));
        r.setPlacementCoordinatorId(1L);
        return r;
    }

    @BeforeEach
    void stubs() {
        when(companyRepo.findById(1L)).thenReturn(Optional.of(new CompanyProfile()));
        when(staffProfileRepo.findById(1L)).thenReturn(Optional.of(new StaffProfile()));
        when(driveRepo.save(any())).thenAnswer(inv -> inv.getArgument(0));
    }

    @Test
    void create_rejectsMissingCoordinator() {
        DriveCreateRequest r = baseRequest();
        r.setPlacementCoordinatorId(null);
        assertThrows(IllegalArgumentException.class, () -> driveCrudService.create(r));
    }

    @Test
    void create_rejectsMoreThanThreeBranches() {
        DriveCreateRequest r = baseRequest();
        r.setAllowedBranches(List.of(
                BranchType.LAW,
                BranchType.SCIENCE,
                BranchType.ARCHITECTURE_DESIGN,
                BranchType.PROFESSIONAL_SPECIALIZED
        ));
        assertThrows(IllegalArgumentException.class, () -> driveCrudService.create(r));
    }

    @Test
    void create_rejectsMoreThanFourRoles() {
        DriveCreateRequest r = baseRequest();
        r.setOfferedRoleTitles(List.of("A", "B", "C", "D", "E"));
        assertThrows(IllegalArgumentException.class, () -> driveCrudService.create(r));
    }

    @Test
    void create_rejectsAnnouncedWithoutResultDate() {
        DriveCreateRequest r = baseRequest();
        r.setResultStatus(JobResultStatus.ANNOUNCED);
        r.setResultDate(null);
        assertThrows(IllegalArgumentException.class, () -> driveCrudService.create(r));
    }

    @Test
    void create_acceptsThreeBranchesAndFourRoles() {
        DriveCreateRequest r = baseRequest();
        r.setAllowedBranches(List.of(BranchType.LAW, BranchType.SCIENCE, BranchType.ARCHITECTURE_DESIGN));
        r.setOfferedRoleTitles(new ArrayList<>(List.of("Dev", "QA", "PM", "Analyst")));
        driveCrudService.create(r);
    }
}
