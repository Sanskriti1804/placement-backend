package com.example.placement.service.crud;

import com.example.placement.dto.placement.IndustryCreateRequest;
import com.example.placement.dto.placement.IndustryResponse;
import com.example.placement.dto.placement.IndustryUpdateRequest;
import com.example.placement.entity.Industry;
import com.example.placement.repository.IndustryRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class IndustryCrudService {

    private final IndustryRepo industryRepo;

    public IndustryCrudService(IndustryRepo industryRepo) {
        this.industryRepo = industryRepo;
    }

    @Transactional
    public IndustryResponse create(IndustryCreateRequest req) {
        if (req.getName() == null || req.getName().isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
        Industry e = new Industry();
        e.setName(req.getName().trim());
        e.setCode(req.getCode());
        e.setDescription(req.getDescription());
        return PlacementDtoMapper.toIndustryResponse(industryRepo.save(e));
    }

    @Transactional
    public IndustryResponse update(Long id, IndustryUpdateRequest req) {
        Industry e = industryRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Industry not found"));
        if (req.getName() != null && !req.getName().isBlank()) {
            e.setName(req.getName().trim());
        }
        if (req.getCode() != null) {
            e.setCode(req.getCode());
        }
        if (req.getDescription() != null) {
            e.setDescription(req.getDescription());
        }
        return PlacementDtoMapper.toIndustryResponse(industryRepo.save(e));
    }

    @Transactional(readOnly = true)
    public IndustryResponse get(Long id) {
        return industryRepo.findById(id).map(PlacementDtoMapper::toIndustryResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Industry not found"));
    }

    @Transactional(readOnly = true)
    public List<IndustryResponse> findAll() {
        return industryRepo.findAll().stream().map(PlacementDtoMapper::toIndustryResponse).toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!industryRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Industry not found");
        }
        industryRepo.deleteById(id);
    }
}
