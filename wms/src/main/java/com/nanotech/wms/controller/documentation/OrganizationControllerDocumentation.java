package com.nanotech.wms.controller.documentation;

import com.nanotech.wms.model.dto.request.OrganizationCreateDto;
import com.nanotech.wms.model.dto.response.OrganizationResponseDto;
import com.nanotech.wms.model.entity.User;
import com.nanotech.wms.model.payload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface OrganizationControllerDocumentation {

    @Operation(summary = "Create a new organization")
    ApiResponse<Void> create(@RequestBody OrganizationCreateDto dto, @AuthenticationPrincipal User user);

    @Operation(summary = "Find an organization by ID")
    ApiResponse<OrganizationResponseDto> findById(@PathVariable UUID id);

    @Operation(summary = "Find all organizations")
    ApiResponse<List<OrganizationResponseDto>> findAll();

    @Operation(summary = "Update an organization by ID")
    ApiResponse<Void> updateOrganization(@PathVariable UUID id, @RequestBody OrganizationCreateDto dto);

    @Operation(summary = "Delete an organization by ID")
    ApiResponse<Void> deleteOrganization(@PathVariable UUID id);
}
