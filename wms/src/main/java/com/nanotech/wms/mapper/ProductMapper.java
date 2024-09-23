package com.nanotech.wms.mapper;

import com.nanotech.wms.exception.CustomNotFoundException;
import com.nanotech.wms.model.dto.request.ProductCreateDto;
import com.nanotech.wms.model.dto.response.ProductResponseDto;
import com.nanotech.wms.model.entity.Product;
import com.nanotech.wms.model.entity.Warehouse;
import com.nanotech.wms.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final WarehouseRepository warehouseRepository;

    public Product toEntity(ProductCreateDto dto) {
        Warehouse warehouse = warehouseRepository.findById(dto.warehouseId())
                .orElseThrow(() -> new CustomNotFoundException("Organization with id %s not found".formatted(dto.warehouseId())));

        Product product = new Product();
        product.setName(dto.name());
        product.setPrice(dto.price());
        product.setAmount(dto.amount());
        product.setWarehouse(warehouse);
        return product;
    }

    public ProductResponseDto toResponse(Product product) {
        return new ProductResponseDto(
                product.getName(),
                product.getPrice(),
                product.getAmount(),
                product.getWarehouse().getId()
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
        Warehouse warehouse = warehouseRepository.findById(dto.warehouseId())
                .orElseThrow(() -> new CustomNotFoundException("Organization with id %s not found".formatted(dto.warehouseId())));
        product.setName(dto.name());
        product.setPrice(dto.price());
        product.setAmount(dto.amount());
        product.setWarehouse(warehouse);
        return product;
    }
}
