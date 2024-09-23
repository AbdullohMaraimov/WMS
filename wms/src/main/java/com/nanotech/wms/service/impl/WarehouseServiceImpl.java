package com.nanotech.wms.service.impl;

import com.nanotech.wms.exception.CustomNotFoundException;
import com.nanotech.wms.mapper.WarehouseMapper;
import com.nanotech.wms.model.dto.request.WarehouseCreateDto;
import com.nanotech.wms.model.dto.response.WarehouseResponseDto;
import com.nanotech.wms.model.entity.User;
import com.nanotech.wms.model.entity.Warehouse;
import com.nanotech.wms.repository.WarehouseRepository;
import com.nanotech.wms.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    @Override
    public void create(WarehouseCreateDto dto, User user) {
        Warehouse entity = warehouseMapper.toEntity(dto);
        entity.setCreatedBy(user);
        warehouseRepository.save(entity);
    }

    @Override
    public WarehouseResponseDto findById(UUID id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Warehouse not found with id %s ".formatted(id)));
        return warehouseMapper.toResponse(warehouse);
    }

    @Override
    public List<WarehouseResponseDto> findAll() {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        return warehouseMapper.toResponses(warehouses);
    }

    @Override
    public void update(UUID id, WarehouseCreateDto dto) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Warehouse not found with id %s ".formatted(id)));
        Warehouse updatedEntity = warehouseMapper.toUpdatedEntity(warehouse, dto);
        warehouseRepository.save(updatedEntity);
    }

    @Override
    public void deleteById(UUID id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Warehouse not found with id %s ".formatted(id)));
        warehouse.setDeleted(true);
        warehouseRepository.save(warehouse);
    }
}
