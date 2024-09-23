package com.nanotech.wms.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record OutgoProductCreateDto(

        @NotNull(message = "OutgoProduct amount can not be null")
        @Positive(message = "OutgoProduct amount can not be negative")
        Long amount,

        @NotNull(message = "OutgoProduct price can not be null")
        @Positive(message = "OutgoProduct price can not be negative")
        Double price,

        @NotNull(message = "OutgoProduct total can not be null")
        @Positive(message = "OutgoProduct total can not be negative")
        Double total,

        @NotNull(message = "OutgoProduct organization id can not be null")
        UUID warehouseId,

        @NotNull(message = "OutgoProduct product id can not be null")
        UUID productId

) { }
