package com.nanotech.wms.mapper;

import com.nanotech.wms.exception.CustomNotFoundException;
import com.nanotech.wms.model.dto.request.ProductCreateDto;
import com.nanotech.wms.model.dto.response.ProductResponseDto;
import com.nanotech.wms.model.entity.Organization;
import com.nanotech.wms.model.entity.Product;
import com.nanotech.wms.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final OrganizationRepository organizationRepository;

    public Product toEntity(ProductCreateDto dto) {
        Organization organization = organizationRepository.findById(dto.organizationId())
                .orElseThrow(() -> new CustomNotFoundException("Organization with id %s not found".formatted(dto.organizationId())));
        Product product = new Product();
        product.setName(dto.name());
        product.setPrice(dto.price());
        product.setOrganization(organization);
        return product;
    }

    public ProductResponseDto toResponse(Product product) {
        return new ProductResponseDto(
                product.getName(),
                product.getPrice(),
                product.getOrganization().getId()
        );
    }

    public List<ProductResponseDto> toResponses(List<Product> products) {
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for (Product product : products) {
            productResponseDtoList.add(toResponse(product));
        }
        return productResponseDtoList;
    }

    public Product toUpdatedEntity(Product product, ProductCreateDto dto) {
        Organization organization = organizationRepository.findById(dto.organizationId())
                .orElseThrow(() -> new CustomNotFoundException("Organization with id %s not found".formatted(dto.organizationId())));
        product.setName(dto.name());
        product.setPrice(dto.price());
        product.setOrganization(organization);
        return product;
    }
}
