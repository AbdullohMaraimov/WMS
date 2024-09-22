package com.nanotech.wms.model.dto.response;

import java.util.UUID;

public record ProductResponseDto(
        String name,
        Double price,
        UUID organizationId
) { }
