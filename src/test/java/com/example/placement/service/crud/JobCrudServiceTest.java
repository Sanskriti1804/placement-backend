package com.example.placement.service.crud;

import com.example.placement.dto.placement.JobCreateRequest;
import com.example.placement.entity.main.CompanyProfile;
import com.example.placement.entity.types.JobType;
import com.example.placement.entity.WorkMode;
import com.example.placement.repository.CompanyRepo;
import com.example.placement.repository.JobRepo;
import com.example.placement.repository.JobSelectionRoundRepo;
import com.example.placement.repository.PlacementCoordinatorRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class JobCrudServiceTest {

    @Mock
    private JobRepo jobRepo;
    @Mock
    private CompanyRepo companyRepo;
    @Mock
    private PlacementCoordinatorRepo coordinatorRepo;
    @Mock
    private JobSelectionRoundRepo jobSelectionRoundRepo;

    @InjectMocks
    private JobCrudService jobCrudService;

    private JobCreateRequest baseRequest() {
        JobCreateRequest r = new JobCreateRequest();
        r.setCompanyId(1L);
        r.setPlacementCoordinatorId(1L);
        r.setJobType(JobType.FULL_TIME);
        r.setWorkMode(WorkMode.REMOTE);
        return r;
    }

    @BeforeEach
    void stubLookups() {
        org.mockito.Mockito.when(companyRepo.findById(1L))
                .thenReturn(java.util.Optional.of(new CompanyProfile()));
        org.mockito.Mockito.when(coordinatorRepo.findById(1L))
                .thenReturn(java.util.Optional.of(new com.example.placement.entity.PlacementCoordinator()));
        org.mockito.Mockito.when(jobRepo.save(org.mockito.Mockito.any()))
                .thenAnswer(inv -> inv.getArgument(0));
    }

    @Test
    void create_rejectsMissingCoordinator() {
        JobCreateRequest r = baseRequest();
        r.setPlacementCoordinatorId(null);
        assertThrows(IllegalArgumentException.class, () -> jobCrudService.create(r));
    }

    @Test
    void create_rejectsInternshipWithoutDuration() {
        JobCreateRequest r = baseRequest();
        r.setJobType(JobType.INTERNSHIP);
        r.setInternshipDuration(null);
        assertThrows(IllegalArgumentException.class, () -> jobCrudService.create(r));
    }

    @Test
    void create_rejectsDurationWhenNotInternship() {
        JobCreateRequest r = baseRequest();
        r.setJobType(JobType.FULL_TIME);
        r.setInternshipDuration("3 months");
        assertThrows(IllegalArgumentException.class, () -> jobCrudService.create(r));
    }
}
