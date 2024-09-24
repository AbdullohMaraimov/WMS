package com.nanotech.wms.controller;

import com.nanotech.wms.model.dto.request.ProductCreateDto;
import com.nanotech.wms.model.dto.response.ProductResponseDto;
import com.nanotech.wms.model.dto.response.ProductStockAmountDto;
import com.nanotech.wms.model.entity.User;
import com.nanotech.wms.model.payload.ApiResponse;
import com.nanotech.wms.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ApiResponse<Void> create(@RequestBody @Valid ProductCreateDto dto, @AuthenticationPrincipal User user) {
        productService.create(dto, user);
        return new ApiResponse<>(true, "Product created successfully!");
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponseDto> findById(@PathVariable UUID id) {
        ProductResponseDto product = productService.findById(id);
        return new ApiResponse<>(true, "Product found", product);
    }

    @GetMapping
    public ApiResponse<List<ProductResponseDto>> findAll() {
        List<ProductResponseDto> products = productService.findAll();
        return new ApiResponse<>(true, "All products fetched", products);
    }

    @GetMapping("/warehouse/{warehouseId}")
    public ApiResponse<List<ProductResponseDto>> findAllByWarehouseId(@PathVariable UUID warehouseId) {
        List<ProductResponseDto> products = productService.findAllByWarehouseId(warehouseId);
        return new ApiResponse<>(true, "Products found for the warehouse", products);
    }

    @GetMapping("/warehouse/{warehouseId}/stock")
    public ApiResponse<List<ProductStockAmountDto>> findAllBetweenDatesInWarehouse(
            @PathVariable UUID warehouseId,
            @RequestParam String fromDate,
            @RequestParam String toDate) {
        LocalDate from = LocalDate.parse(fromDate);
        LocalDate to = LocalDate.parse(toDate);

        LocalDateTime fromTime = from.atStartOfDay();
        Timestamp fromTimestamp = Timestamp.valueOf(fromTime);

        LocalDateTime localDateTime = to.atStartOfDay();
        Timestamp toTimestamp = Timestamp.valueOf(localDateTime);

        List<ProductStockAmountDto> stockList = productService.findAllBetweenDatesInWarehouse(warehouseId, fromTimestamp, toTimestamp);
        return new ApiResponse<>(true, "Stock fetched for warehouse between dates", stockList);
    }

    @GetMapping("/organization/{orgId}/stock")
    public ApiResponse<List<ProductStockAmountDto>> findAllBetweenDatesInOrganization(
            @PathVariable UUID orgId,
            @RequestParam String fromDate,
            @RequestParam String toDate) {
        LocalDate from = LocalDate.parse(fromDate);
        LocalDate to = LocalDate.parse(toDate);

        LocalDateTime fromTime = from.atStartOfDay();
        Timestamp fromTimestamp = Timestamp.valueOf(fromTime);

        LocalDateTime localDateTime = to.atStartOfDay();
        Timestamp toTimestamp = Timestamp.valueOf(localDateTime);

        List<ProductStockAmountDto> stockList = productService.findAllBetweenDatesInOrganization(orgId, fromTimestamp, toTimestamp);
        return new ApiResponse<>(true, "Stock fetched for organization between dates", stockList);
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> updateProduct(@PathVariable UUID id, @RequestBody ProductCreateDto dto) {
        productService.update(id, dto);
        return new ApiResponse<>(true, "Product updated successfully");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteById(id);
        return new ApiResponse<>(true, "Product deleted successfully");
    }

}
