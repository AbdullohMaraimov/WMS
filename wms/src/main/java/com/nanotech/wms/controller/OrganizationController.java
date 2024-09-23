package com.nanotech.wms.controller;

import com.nanotech.wms.model.dto.request.OrganizationCreateDto;
import com.nanotech.wms.model.entity.User;
import com.nanotech.wms.model.payload.ApiResponse;
import com.nanotech.wms.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    public ApiResponse<Void> create(@RequestBody @Valid OrganizationCreateDto dto, @AuthenticationPrincipal User user) {
        organizationService.create(dto, user);
        return new ApiResponse<>(true, "Organization created successfully");
    }

}
