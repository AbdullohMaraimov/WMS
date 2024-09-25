package com.nanotech.wms.controller.documentation;

import com.nanotech.wms.model.dto.request.WarehouseCreateDto;
import com.nanotech.wms.model.dto.response.WarehouseResponseDto;
import com.nanotech.wms.model.entity.User;
import com.nanotech.wms.model.payload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface WarehouseControllerDocumentation {

    @Operation(summary = "Create a new warehouse")
    ApiResponse<Void> create(@RequestBody WarehouseCreateDto dto, @AuthenticationPrincipal User user);

    @Operation(summary = "Find a warehouse by ID")
    ApiResponse<WarehouseResponseDto> findById(@PathVariable UUID id);

    @Operation(summary = "Find all warehouses")
    ApiResponse<List<WarehouseResponseDto>> findAll();

    @Operation(summary = "Update a warehouse by ID")
    ApiResponse<Void> updateWarehouse(@PathVariable UUID id, @RequestBody WarehouseCreateDto dto);

    @Operation(summary = "Delete a warehouse by ID")
    ApiResponse<Void> deleteWarehouse(@PathVariable UUID id);
}
