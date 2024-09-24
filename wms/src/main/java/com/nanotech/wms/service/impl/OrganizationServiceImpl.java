package com.nanotech.wms.service.impl;

import com.nanotech.wms.exception.CustomNotFoundException;
import com.nanotech.wms.mapper.OrganizationMapper;
import com.nanotech.wms.model.dto.request.OrganizationCreateDto;
import com.nanotech.wms.model.dto.response.OrganizationResponseDto;
import com.nanotech.wms.model.entity.Organization;
import com.nanotech.wms.model.entity.User;
import com.nanotech.wms.repository.OrganizationRepository;
import com.nanotech.wms.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    @Override
    public void create(OrganizationCreateDto dto, User user) {
        Organization organization = organizationMapper.toEntity(dto);
        organization.setCreatedBy(user);
        organizationRepository.save(organization);
    }

    @Override
    public OrganizationResponseDto findById(UUID id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Organization with id %s not found".formatted(id)));
        return organizationMapper.toResponse(organization);
    }

    @Override
    public List<OrganizationResponseDto> findAll() {
        List<Organization> organizations = organizationRepository.findAll();
        return organizationMapper.toResponses(organizations);
    }

    @Override
    public void update(UUID uuid, OrganizationCreateDto dto) {
        Organization organization = organizationRepository.findById(uuid)
                .orElseThrow(() -> new CustomNotFoundException("Organization not found"));
        Organization newOrg = organizationMapper.toUpdatedEntity(organization, dto);
        organizationRepository.save(newOrg);
    }

    @Override
    public void deleteById(UUID id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Organization with id %s not found".formatted(id)));
        organization.setDeleted(true);
        organizationRepository.save(organization);
    }
}
