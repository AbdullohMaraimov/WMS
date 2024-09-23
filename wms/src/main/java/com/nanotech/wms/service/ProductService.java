package com.nanotech.wms.service;

import com.nanotech.wms.model.dto.request.ProductCreateDto;
import com.nanotech.wms.model.dto.response.ProductResponseDto;
import com.nanotech.wms.model.entity.User;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    void create(ProductCreateDto dto, User user);

    ProductResponseDto findById(UUID id);

    List<ProductResponseDto> findAll();

    void update(UUID uuid, ProductCreateDto dto);

    void deleteById(UUID id);

}
