package com.nanotech.wms.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record IncomeProductCreateDto(

        @NotNull(message = "IncomeProduct amount can not be null")
        @Positive(message = "IncomeProduct amount can not be negative")
        Long amount,

        @NotNull(message = "IncomeProduct price can not be null")
        @Positive(message = "IncomeProduct price can not be negative")
        Double price,

        @NotNull(message = "IncomeProduct total can not be null")
        @Positive(message = "IncomeProduct total can not be negative")
        Double total,

        @NotNull(message = "IncomeProduct warehouseId can not be null")
        UUID warehouseId,

        @NotNull(message = "IncomeProduct product id can not be null")
        UUID productId

) { }
