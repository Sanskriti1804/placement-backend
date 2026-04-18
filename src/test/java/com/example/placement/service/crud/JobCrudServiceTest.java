package com.example.placement.service.crud;

import com.example.placement.dto.job.JobCreateRequest;
import com.example.placement.entity.main.CompanyProfile;
import com.example.placement.entity.main.StaffProfile;
import com.example.placement.common.enums.JobType;
import com.example.placement.common.enums.WorkMode;
import com.example.placement.repository.CompanyRepo;
import com.example.placement.repository.JobRepo;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class JobCrudServiceTest {

    @Mock
    private JobRepo jobRepo;
    @Mock
    private CompanyRepo companyRepo;
    @Mock
    private StaffProfileRepo staffProfileRepo;
    @Mock
    private SelectionRoundRepo selectionRoundRepo;

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
        when(companyRepo.findById(1L))
                .thenReturn(java.util.Optional.of(new CompanyProfile()));
        when(staffProfileRepo.findById(1L))
                .thenReturn(java.util.Optional.of(new StaffProfile()));
        when(jobRepo.save(any()))
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
