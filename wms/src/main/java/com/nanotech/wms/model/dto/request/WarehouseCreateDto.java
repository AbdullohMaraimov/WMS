package com.nanotech.wms.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record WarehouseCreateDto(
        @NotNull(message = "Warehouse cant be null")
        @NotBlank(message = "Warehouse cant be blank")
        String name,

        @NotNull(message = "OrganizationId cant be null")
        UUID OrganizationId
) { }
