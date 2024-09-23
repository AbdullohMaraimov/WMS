package com.nanotech.wms.mapper;

import com.nanotech.wms.exception.CustomNotFoundException;
import com.nanotech.wms.model.dto.request.IncomeProductCreateDto;
import com.nanotech.wms.model.dto.response.IncomeProductResponseDto;
import com.nanotech.wms.model.entity.*;
import com.nanotech.wms.repository.InvoiceRepository;
import com.nanotech.wms.repository.OrganizationRepository;
import com.nanotech.wms.repository.ProductRepository;
import com.nanotech.wms.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class IncomeProductMapper {

    private final OrganizationRepository organizationRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public IncomeProduct toEntity(IncomeProductCreateDto dto) {
        IncomeProduct incomeProduct = new IncomeProduct();

        incomeProduct.setAmount(dto.amount());
        incomeProduct.setTotal(dto.total());
        incomeProduct.setPrice(dto.price());

        Warehouse warehouse = warehouseRepository.findById(dto.warehouseId())
                .orElseThrow(() -> new CustomNotFoundException("Warehouse with id %s not found".formatted(dto.warehouseId())));

        Product product = productRepository.findById(dto.productId())
                .orElseThrow(() -> new CustomNotFoundException("Product with id %s not found".formatted(dto.productId())));

        incomeProduct.setWarehouse(warehouse);
        incomeProduct.setProduct(product);

        return incomeProduct;
    }

    public IncomeProductResponseDto toResponse(IncomeProduct incomeProduct) {
        return new IncomeProductResponseDto(
                incomeProduct.getNum(),
                incomeProduct.getAmount(),
                incomeProduct.getPrice(),
                incomeProduct.getTotal(),
                incomeProduct.getWarehouse().getId(),
                incomeProduct.getInvoice().getId(),
                incomeProduct.getProduct().getId()
        );
    }

    public List<IncomeProductResponseDto> toResponses(List<IncomeProduct> incomeProducts) {
        List<IncomeProductResponseDto> incomeProductResponseDtoList = new ArrayList<>();
        for (IncomeProduct incomeProduct : incomeProducts) {
            incomeProductResponseDtoList.add(toResponse(incomeProduct));
        }
        return incomeProductResponseDtoList;
    }

    public IncomeProduct toUpdatedEntity(IncomeProduct incomeProduct, IncomeProductCreateDto dto) {
        incomeProduct.setAmount(dto.amount());
        incomeProduct.setTotal(dto.total());
        incomeProduct.setPrice(dto.price());

        Warehouse warehouse = warehouseRepository.findById(dto.warehouseId())
                .orElseThrow(() -> new CustomNotFoundException("Warehouse with id %s not found".formatted(dto.warehouseId())));

        Product product = productRepository.findById(dto.productId())
                .orElseThrow(() -> new CustomNotFoundException("Product with id %s not found".formatted(dto.productId())));

        incomeProduct.setWarehouse(warehouse);
        incomeProduct.setProduct(product);

        return incomeProduct;
    }

    public List<IncomeProduct> toEntities(List<IncomeProductCreateDto> incomeProductCreateDtos) {
        List<IncomeProduct> incomeProducts = new ArrayList<>();
        for (IncomeProductCreateDto incomeProductCreateDto : incomeProductCreateDtos) {
            incomeProducts.add(toEntity(incomeProductCreateDto));
        }
        return incomeProducts;
    }
}
