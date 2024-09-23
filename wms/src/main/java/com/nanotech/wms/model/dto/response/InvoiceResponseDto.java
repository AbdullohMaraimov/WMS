package com.nanotech.wms.model.dto.response;

import com.nanotech.wms.model.constant.InvoiceStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record InvoiceResponseDto(

        UUID id,

        InvoiceStatus status,

        UUID organizationId,

        UUID customerId,

        UUID warehouseI

) { }
