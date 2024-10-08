package com.nanotech.wms.service;

import com.nanotech.wms.model.dto.request.WarehouseCreateDto;
import com.nanotech.wms.model.dto.response.WarehouseResponseDto;
import com.nanotech.wms.model.entity.User;

import java.util.List;
import java.util.UUID;

public interface WarehouseService {

    void create(WarehouseCreateDto dto, User user);

    WarehouseResponseDto findById(UUID id);

    List<WarehouseResponseDto> findAll();

    void update(UUID uuid, WarehouseCreateDto dto);

    void deleteById(UUID id);

}
