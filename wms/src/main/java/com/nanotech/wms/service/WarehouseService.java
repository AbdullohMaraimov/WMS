package com.nanotech.wms.service;

import com.nanotech.wms.model.dto.request.ProductCreateDto;
import com.nanotech.wms.model.dto.request.WarehouseCreateDto;
import com.nanotech.wms.model.dto.response.ProductResponseDto;
import com.nanotech.wms.model.dto.response.WarehouseResponseDto;

import java.util.List;
import java.util.UUID;

public interface WarehouseService {

    void create(WarehouseCreateDto dto);

    WarehouseResponseDto findById(UUID id);

    List<WarehouseResponseDto> findAll();

    void update(UUID uuid, WarehouseCreateDto dto);

    void deleteById(UUID id);

}
