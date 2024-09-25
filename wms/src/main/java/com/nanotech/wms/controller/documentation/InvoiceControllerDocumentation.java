package com.nanotech.wms.controller.documentation;

import com.nanotech.wms.model.dto.request.InvoiceCreateDto;
import com.nanotech.wms.model.dto.response.InvoiceResponseDto;
import com.nanotech.wms.model.entity.User;
import com.nanotech.wms.model.payload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface InvoiceControllerDocumentation {

    @Operation(summary = "Create a new invoice")
    ApiResponse<Void> create(@RequestBody InvoiceCreateDto dto, @AuthenticationPrincipal User user);

    @Operation(summary = "Find an invoice by ID")
    ApiResponse<InvoiceResponseDto> findById(@PathVariable UUID id);

    @Operation(summary = "Find all invoices")
    ApiResponse<List<InvoiceResponseDto>> findAll();

    @Operation(summary = "Delete an invoice by ID")
    ApiResponse<Void> deleteById(@PathVariable UUID id);
}
