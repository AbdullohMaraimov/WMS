package com.nanotech.wms.model.dto.response;

import java.util.UUID;

public record WarehouseResponseDto(
        String name,
        UUID organizationId
) { }
