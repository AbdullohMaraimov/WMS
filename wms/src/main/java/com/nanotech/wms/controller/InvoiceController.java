package com.nanotech.wms.controller;

import com.nanotech.wms.model.dto.request.InvoiceCreateDto;
import com.nanotech.wms.model.dto.response.InvoiceResponseDto;
import com.nanotech.wms.model.entity.User;
import com.nanotech.wms.model.payload.ApiResponse;
import com.nanotech.wms.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping
    public ApiResponse<Void> create(@RequestBody InvoiceCreateDto dto, @AuthenticationPrincipal User user) {
        invoiceService.create(dto, user);
        return new ApiResponse<>(true, "Invoice created successfully!");
    }

    @GetMapping("/{id}")
    public ApiResponse<InvoiceResponseDto> findById(@PathVariable UUID id) {
        InvoiceResponseDto invoice = invoiceService.findById(id);
        return new ApiResponse<>(true, "Invoice found", invoice);
    }

    @GetMapping
    public ApiResponse<List<InvoiceResponseDto>> findAll() {
        List<InvoiceResponseDto> invoices = invoiceService.findAll();
        return new ApiResponse<>(true, "All invoices fetched", invoices);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteById(@PathVariable UUID id) {
        invoiceService.deleteById(id);
        return new ApiResponse<>(true, "Invoice deleted successfully");
    }

}
