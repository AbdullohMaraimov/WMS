package com.nanotech.wms.controller.documentation;

import com.nanotech.wms.model.dto.request.ProductCreateDto;
import com.nanotech.wms.model.dto.response.ProductResponseDto;
import com.nanotech.wms.model.dto.response.ProductStockAmountDto;
import com.nanotech.wms.model.entity.User;
import com.nanotech.wms.model.payload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

public interface ProductControllerDocumentation {

    @Operation(summary = "Create a new product")
    ApiResponse<Void> create(@RequestBody ProductCreateDto dto, @AuthenticationPrincipal User user);

    @Operation(summary = "Find a product by ID")
    ApiResponse<ProductResponseDto> findById(@PathVariable UUID id);

    @Operation(summary = "Find all products")
    ApiResponse<List<ProductResponseDto>> findAll();

    @Operation(summary = "Find all products in a warehouse")
    ApiResponse<List<ProductResponseDto>> findAllByWarehouseId(@PathVariable UUID warehouseId);

    @Operation(summary = "Get stock of products in a warehouse between dates")
    ApiResponse<List<ProductStockAmountDto>> findAllBetweenDatesInWarehouse(
            @PathVariable UUID warehouseId,
            @RequestParam String fromDate,
            @RequestParam String toDate);

    @Operation(summary = "Get stock of products in an organization between dates")
    ApiResponse<List<ProductStockAmountDto>> findAllBetweenDatesInOrganization(
            @PathVariable UUID orgId,
            @RequestParam String fromDate,
            @RequestParam String toDate);

    @Operation(summary = "Update a product by ID")
    ApiResponse<Void> updateProduct(@PathVariable UUID id, @RequestBody ProductCreateDto dto);

    @Operation(summary = "Delete a product by ID")
    ApiResponse<Void> deleteProduct(@PathVariable UUID id);
}