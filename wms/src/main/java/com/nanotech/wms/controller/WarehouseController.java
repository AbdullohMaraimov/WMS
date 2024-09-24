package com.nanotech.wms.controller;

import com.nanotech.wms.model.dto.request.WarehouseCreateDto;
import com.nanotech.wms.model.dto.response.WarehouseResponseDto;
import com.nanotech.wms.model.entity.User;
import com.nanotech.wms.model.payload.ApiResponse;
import com.nanotech.wms.service.WarehouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/warehouse")
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PostMapping
    public ApiResponse<Void> create(@RequestBody @Valid WarehouseCreateDto dto, @AuthenticationPrincipal User user) {
        warehouseService.create(dto, user);
        return new ApiResponse<>(true, "Warehouse successfully created!");
    }

    @GetMapping("/{id}")
    public ApiResponse<WarehouseResponseDto> findById(@PathVariable UUID id) {
        WarehouseResponseDto warehouse = warehouseService.findById(id);
        return new ApiResponse<>(true, "Warehouse found", warehouse);
    }

    @GetMapping
    public ApiResponse<List<WarehouseResponseDto>> findAll() {
        List<WarehouseResponseDto> warehouses = warehouseService.findAll();
        return new ApiResponse<>(true, "All warehouses fetched", warehouses);
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> updateWarehouse(@PathVariable UUID id, @RequestBody WarehouseCreateDto dto) {
        warehouseService.update(id, dto);
        return new ApiResponse<>(true, "Warehouse updated successfully");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteWarehouse(@PathVariable UUID id) {
        warehouseService.deleteById(id);
        return new ApiResponse<>(true, "Warehouse deleted successfully");
    }

}
