package com.nanotech.wms.mapper;

import com.nanotech.wms.model.dto.request.OrganizationCreateDto;
import com.nanotech.wms.model.dto.response.OrganizationResponseDto;
import com.nanotech.wms.model.entity.Organization;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrganizationMapper {

    public Organization toEntity(OrganizationCreateDto dto) {
        Organization organization = new Organization();
        organization.setName(dto.name());
        organization.setTin(dto.tin());
        return organization;
    }

    public OrganizationResponseDto toResponse(Organization organization) {
        return new OrganizationResponseDto(
                organization.getName(),
                organization.getTin()
        );
    }

    public List<OrganizationResponseDto> toResponses(List<Organization> organizations) {
        List<OrganizationResponseDto> responseDtoList = new ArrayList<>();
        for (Organization org : organizations) {
            responseDtoList.add(toResponse(org));
        }
        return responseDtoList;
    }

    public Organization toUpdatedEntity(Organization organization, OrganizationCreateDto dto) {
        organization.setTin(dto.tin());
        organization.setName(dto.name());
        return organization;
    }
}
