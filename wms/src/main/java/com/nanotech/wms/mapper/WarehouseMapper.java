package com.nanotech.wms.mapper;

import com.nanotech.wms.exception.CustomNotFoundException;
import com.nanotech.wms.model.dto.request.OrganizationCreateDto;
import com.nanotech.wms.model.dto.request.WarehouseCreateDto;
import com.nanotech.wms.model.dto.response.OrganizationResponseDto;
import com.nanotech.wms.model.dto.response.WarehouseResponseDto;
import com.nanotech.wms.model.entity.Organization;
import com.nanotech.wms.model.entity.Warehouse;
import com.nanotech.wms.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WarehouseMapper {

    private final OrganizationRepository organizationRepository;

    public Warehouse toEntity(WarehouseCreateDto dto) {
        Organization organization = organizationRepository.findById(dto.OrganizationId())
                .orElseThrow(() -> new CustomNotFoundException("Organization not found with id %s".formatted(dto.OrganizationId())));

        Warehouse warehouse = new Warehouse();
        warehouse.setOrganization(organization);
        warehouse.setName(dto.name());
        return warehouse;
    }

    public WarehouseResponseDto toResponse(Warehouse warehouse) {
        return new WarehouseResponseDto(
                warehouse.getName(),
                warehouse.getOrganization().getId()
        );
    }

    public List<WarehouseResponseDto> toResponses(List<Warehouse> warehouses) {
        List<WarehouseResponseDto> responseDtoList = new ArrayList<>();
        for (Warehouse org : warehouses) {
            responseDtoList.add(toResponse(org));
        }
        return responseDtoList;
    }

    public Warehouse toUpdatedEntity(Warehouse warehouse, WarehouseCreateDto dto) {
        Organization organization = organizationRepository.findById(dto.OrganizationId())
                .orElseThrow(() -> new CustomNotFoundException("Organization not found with id %s".formatted(dto.OrganizationId())));

        warehouse.setName(dto.name());
        warehouse.setOrganization(organization);
        return warehouse;
    }
}
