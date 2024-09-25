package com.nanotech.wms.controller;

import com.nanotech.wms.controller.documentation.OrganizationControllerDocumentation;
import com.nanotech.wms.model.dto.request.OrganizationCreateDto;
import com.nanotech.wms.model.dto.response.OrganizationResponseDto;
import com.nanotech.wms.model.entity.User;
import com.nanotech.wms.model.payload.ApiResponse;
import com.nanotech.wms.repository.OrganizationRepository;
import com.nanotech.wms.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/organization")
@RequiredArgsConstructor
public class OrganizationController implements OrganizationControllerDocumentation {

    private final OrganizationService organizationService;
    private final OrganizationRepository organizationRepository;

    @PostMapping
    public ApiResponse<Void> create(@RequestBody @Valid OrganizationCreateDto dto, @AuthenticationPrincipal User user) {
        organizationService.create(dto, user);
        return new ApiResponse<>(true, "Organization created successfully");
    }

    @GetMapping("/{id}")
    public ApiResponse<OrganizationResponseDto> findById(@PathVariable UUID id) {
        OrganizationResponseDto organization = organizationService.findById(id);
        return new ApiResponse<>(true, "Organization found", organization);
    }

    @GetMapping
    public ApiResponse<List<OrganizationResponseDto>> findAll() {
        List<OrganizationResponseDto> organizations = organizationService.findAll();
        return new ApiResponse<>(true, "All organizations fetched", organizations);
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> updateOrganization(@PathVariable UUID id, @RequestBody OrganizationCreateDto dto) {
        organizationService.update(id, dto);
        return new ApiResponse<>(true, "Organization updated successfully");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteOrganization(@PathVariable UUID id) {
        organizationService.deleteById(id);
        return new ApiResponse<>(true, "Organization deleted successfully");
    }

}

