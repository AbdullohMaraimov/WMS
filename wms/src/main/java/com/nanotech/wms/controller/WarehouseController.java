package com.nanotech.wms.controller;

import com.nanotech.wms.model.dto.request.WarehouseCreateDto;
import com.nanotech.wms.model.entity.User;
import com.nanotech.wms.model.payload.ApiResponse;
import com.nanotech.wms.service.WarehouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
