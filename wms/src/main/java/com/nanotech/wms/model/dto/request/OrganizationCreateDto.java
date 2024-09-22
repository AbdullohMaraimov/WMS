package com.nanotech.wms.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrganizationCreateDto(
        @NotNull(message = "Organization name cant be null")
        @NotBlank(message = "Organization name cant be blank")
        String name,

        @NotNull(message = "Organization tin cant be null")
        @NotBlank(message = "Organization tin cant be blank")
        String tin
) { }
