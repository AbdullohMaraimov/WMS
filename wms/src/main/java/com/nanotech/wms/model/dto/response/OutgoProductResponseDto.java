package com.nanotech.wms.model.dto.response;

import java.util.UUID;

public record OutgoProductResponseDto(

        Integer num,

        Long amount,

        Double price,

        Double total,

        UUID organizationId,

        UUID invoiceId,

        UUID productId

) { }
