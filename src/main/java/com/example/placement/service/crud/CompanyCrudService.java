package com.example.placement.service.crud;

import com.example.placement.dto.company.CompanyContactSupportRequest;
import com.example.placement.dto.company.CompanyCreateRequest;
import com.example.placement.dto.company.CompanyResponse;
import com.example.placement.dto.company.CompanyUpdateRequest;
import com.example.placement.entity.CompanyContactSupport;
import com.example.placement.entity.main.CompanyProfile;
import com.example.placement.repository.CompanyRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyCrudService {

    private final CompanyRepo companyRepo;

    public CompanyCrudService(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    private static void applyContactSupports(CompanyProfile e, List<CompanyContactSupportRequest> reqs) {
        if (reqs == null) {
            return;
        }
        e.getContactSupports().clear();
        for (CompanyContactSupportRequest r : reqs) {
            if (r.getName() == null || r.getName().isBlank() || r.getEmail() == null || r.getEmail().isBlank()) {
                throw new IllegalArgumentException("Each contact support entry requires name and email");
            }
            CompanyContactSupport c = new CompanyContactSupport();
            c.setCompany(e);
            c.setName(r.getName().trim());
            c.setEmail(r.getEmail().trim());
            c.setPhone(r.getPhone());
            c.setPreferredMode(r.getPreferredMode());
            e.getContactSupports().add(c);
        }
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
        e.setOverview(req.getOverview());
        e.setSector(req.getSector());
        e.setImageUrl(req.getImageUrl());
        if (req.getDocumentUrls() != null) {
            e.setDocumentUrls(new ArrayList<>(req.getDocumentUrls()));
        }
        applyContactSupports(e, req.getContactSupports());
        CompanyProfile saved = companyRepo.save(e);
        saved.getContactSupports().size();
        return PlacementDtoMapper.toCompanyResponse(saved);
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
        if (req.getOverview() != null) {
            e.setOverview(req.getOverview());
        }
        if (req.getSector() != null) {
            e.setSector(req.getSector());
        }
        if (req.getImageUrl() != null) {
            e.setImageUrl(req.getImageUrl());
        }
        if (req.getDocumentUrls() != null) {
            e.setDocumentUrls(new ArrayList<>(req.getDocumentUrls()));
        }
        if (req.getContactSupports() != null) {
            applyContactSupports(e, req.getContactSupports());
        }
        CompanyProfile saved = companyRepo.save(e);
        saved.getContactSupports().size();
        return PlacementDtoMapper.toCompanyResponse(saved);
    }

    @Transactional(readOnly = true)
    public CompanyResponse get(Long id) {
        CompanyProfile e = companyRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));
        e.getContactSupports().size();
        return PlacementDtoMapper.toCompanyResponse(e);
    }

    @Transactional(readOnly = true)
    public List<CompanyResponse> findAll() {
        return companyRepo.findAll().stream().map(c -> {
            c.getContactSupports().size();
            return PlacementDtoMapper.toCompanyResponse(c);
        }).toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!companyRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
        }
        companyRepo.deleteById(id);
    }
}
