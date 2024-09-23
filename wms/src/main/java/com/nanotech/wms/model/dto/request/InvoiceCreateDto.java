package com.nanotech.wms.model.dto.request;

import com.nanotech.wms.model.constant.InvoiceStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;
import java.util.UUID;

public record InvoiceCreateDto(

        @NotNull(message = "Invoice status can not be null")
        InvoiceStatus status,

        @NotNull(message = "Organization id can not be null")
        @Positive(message = "Organization id can not be null")
        UUID organizationId,

        @NotNull(message = "Customer id can not be null")
        @Positive(message = "Customer id can not be null")
        UUID customerId,

        @NotNull(message = "Warehouse id can not be null")
        @Positive(message = "Warehouse id can not be null")
        UUID warehouseId,

        List<IncomeProductCreateDto> incomeProductsList,

        List<OutgoProductCreateDto> outgoProductList
) { }
