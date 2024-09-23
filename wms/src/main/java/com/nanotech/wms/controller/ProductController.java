package com.nanotech.wms.controller;

import com.nanotech.wms.model.dto.request.ProductCreateDto;
import com.nanotech.wms.model.entity.User;
import com.nanotech.wms.model.payload.ApiResponse;
import com.nanotech.wms.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
