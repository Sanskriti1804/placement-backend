package com.example.placement.service.crud;

import com.example.placement.dto.placement.CompanyCreateRequest;
import com.example.placement.dto.placement.CompanyResponse;
import com.example.placement.dto.placement.CompanyUpdateRequest;
import com.example.placement.entity.main.CompanyProfile;
import com.example.placement.repository.CompanyRepo;
import com.example.placement.repository.IndustryRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CompanyCrudService {

    private final CompanyRepo companyRepo;
    private final IndustryRepo industryRepo;

    public CompanyCrudService(CompanyRepo companyRepo, IndustryRepo industryRepo) {
        this.companyRepo = companyRepo;
        this.industryRepo = industryRepo;
    }

    @Transactional
    public CompanyResponse create(CompanyCreateRequest req) {
        if (req.getName() == null || req.getName().isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
        CompanyProfile e = new CompanyProfile();
        e.setName(req.getName().trim());
        e.setTagline(req.getTagline());
        e.setLocation(req.getLocation());
        e.setEmail(req.getEmail());
        e.setWebsiteUrl(req.getWebsiteUrl());
        e.setDescription(req.getDescription());
        e.setImageUrl(req.getImageUrl());
        if (req.getIndustryId() != null) {
            e.setIndustry(industryRepo.findById(req.getIndustryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Industry not found")));
        }
        return PlacementDtoMapper.toCompanyResponse(companyRepo.save(e));
    }

    @Transactional
    public CompanyResponse update(Long id, CompanyUpdateRequest req) {
        CompanyProfile e = companyRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));
        if (req.getName() != null && !req.getName().isBlank()) {
            e.setName(req.getName().trim());
        }
        if (req.getTagline() != null) {
            e.setTagline(req.getTagline());
        }
        if (req.getLocation() != null) {
            e.setLocation(req.getLocation());
        }
        if (req.getEmail() != null) {
            e.setEmail(req.getEmail());
        }
        if (req.getWebsiteUrl() != null) {
            e.setWebsiteUrl(req.getWebsiteUrl());
        }
        if (req.getDescription() != null) {
            e.setDescription(req.getDescription());
        }
        if (req.getImageUrl() != null) {
            e.setImageUrl(req.getImageUrl());
        }
        if (req.getIndustryId() != null) {
            e.setIndustry(industryRepo.findById(req.getIndustryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Industry not found")));
        }
        return PlacementDtoMapper.toCompanyResponse(companyRepo.save(e));
    }

    @Transactional(readOnly = true)
    public CompanyResponse get(Long id) {
        return companyRepo.findById(id).map(PlacementDtoMapper::toCompanyResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));
    }

    @Transactional(readOnly = true)
    public List<CompanyResponse> findAll() {
        return companyRepo.findAll().stream().map(PlacementDtoMapper::toCompanyResponse).toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!companyRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
        }
        companyRepo.deleteById(id);
    }
}
