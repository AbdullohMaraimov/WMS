package com.nanotech.wms.service;

import com.nanotech.wms.model.dto.request.OrganizationCreateDto;
import com.nanotech.wms.model.dto.response.OrganizationResponseDto;
import com.nanotech.wms.model.entity.Organization;

import java.util.List;
import java.util.UUID;

public interface OrganizationService {

    void create(OrganizationCreateDto dto);

    OrganizationResponseDto findById(UUID id);

    List<OrganizationResponseDto> findAll();

    void update(Organization organization, OrganizationCreateDto dto);

    void deleteById(UUID id);

}
