package com.example.placement.service.crud;

import com.example.placement.dto.placement.DriveCreateRequest;
import com.example.placement.entity.types.BranchType;
import com.example.placement.entity.types.JobResultStatus;
import com.example.placement.entity.main.CompanyProfile;
import com.example.placement.entity.PlacementCoordinator;
import com.example.placement.repository.CompanyRepo;
import com.example.placement.repository.DriveRepo;
import com.example.placement.repository.DriveSelectionRoundRepo;
import com.example.placement.repository.PlacementCoordinatorRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DriveCrudServiceTest {

    @Mock
    private DriveRepo driveRepo;
    @Mock
    private CompanyRepo companyRepo;
    @Mock
    private PlacementCoordinatorRepo coordinatorRepo;
    @Mock
    private DriveSelectionRoundRepo driveSelectionRoundRepo;

    @InjectMocks
    private DriveCrudService driveCrudService;

    private DriveCreateRequest baseRequest() {
        DriveCreateRequest r = new DriveCreateRequest();
        r.setDriveName("Winter placements");
        r.setCompanyId(1L);
        r.setRegistrationDeadline(LocalDateTime.now().plusDays(14));
        r.setPlacementCoordinatorId(1L);
        return r;
    }

    @BeforeEach
    void stubs() {
        when(companyRepo.findById(1L)).thenReturn(Optional.of(new CompanyProfile()));
        when(coordinatorRepo.findById(1L)).thenReturn(Optional.of(new PlacementCoordinator()));
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
