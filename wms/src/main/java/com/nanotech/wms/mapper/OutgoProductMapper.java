package com.nanotech.wms.mapper;

import com.nanotech.wms.exception.CustomNotFoundException;
import com.nanotech.wms.model.dto.request.OutgoProductCreateDto;
import com.nanotech.wms.model.dto.response.OutgoProductResponseDto;
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
public class OutgoProductMapper {


    private final OrganizationRepository organizationRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public OutgoProduct toEntity(OutgoProductCreateDto dto) {
        OutgoProduct outgoProduct = new OutgoProduct();

        outgoProduct.setAmount(dto.amount());
        outgoProduct.setTotal(dto.total());
        outgoProduct.setPrice(dto.price());

        Warehouse warehouse = warehouseRepository.findById(dto.warehouseId())
                .orElseThrow(() -> new CustomNotFoundException("Warehouse with id %s not found".formatted(dto.warehouseId())));


        Product product = productRepository.findById(dto.productId())
                .orElseThrow(() -> new CustomNotFoundException("Product with id %s not found".formatted(dto.productId())));

        outgoProduct.setWarehouse(warehouse);
        outgoProduct.setProduct(product);

        return outgoProduct;
    }

    public OutgoProductResponseDto toResponse(OutgoProduct outgoProduct) {
        return new OutgoProductResponseDto(
                outgoProduct.getNum(),
                outgoProduct.getAmount(),
                outgoProduct.getPrice(),
                outgoProduct.getTotal(),
                outgoProduct.getWarehouse().getId(),
                outgoProduct.getInvoice().getId(),
                outgoProduct.getProduct().getId()
        );
    }

    public List<OutgoProductResponseDto> toResponses(List<OutgoProduct> outgoProducts) {
        List<OutgoProductResponseDto> outgoProductResponseDtoList = new ArrayList<>();
        for (OutgoProduct outgoProduct : outgoProducts) {
            outgoProductResponseDtoList.add(toResponse(outgoProduct));
        }
        return outgoProductResponseDtoList;
    }

    public OutgoProduct toUpdatedEntity(OutgoProduct outgoProduct, OutgoProductCreateDto dto) {
        outgoProduct.setAmount(dto.amount());
        outgoProduct.setTotal(dto.total());
        outgoProduct.setPrice(dto.price());

        Warehouse warehouse = warehouseRepository.findById(dto.warehouseId())
                .orElseThrow(() -> new CustomNotFoundException("Organization with id %s not found".formatted(dto.warehouseId())));

        Product product = productRepository.findById(dto.productId())
                .orElseThrow(() -> new CustomNotFoundException("Product with id %s not found".formatted(dto.productId())));

        outgoProduct.setWarehouse(warehouse);
        outgoProduct.setProduct(product);

        return outgoProduct;
    }

    public List<OutgoProduct> toEntities(List<OutgoProductCreateDto> outgoProductCreateDtos) {
        List<OutgoProduct> outgoProducts = new ArrayList<>();
        for (OutgoProductCreateDto outgoProductCreateDto : outgoProductCreateDtos) {
            outgoProducts.add(toEntity(outgoProductCreateDto));
        }
        return outgoProducts;
    }
}
