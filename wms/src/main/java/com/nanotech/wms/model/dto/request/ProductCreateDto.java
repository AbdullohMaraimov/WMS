package com.nanotech.wms.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ProductCreateDto(
        @NotNull(message = "Product name can not be null")
        @NotBlank(message = "Product name can not be blank")
        String name,

        @NotNull(message = "Price name can not be null")
        @Positive(message = "Price name can not be negative")
        Double price,

        @NotNull(message = "organizationId can not be null")
        @Positive(message = "organizationId can not be negative")
        UUID organizationId
) { }
