package com.nanotech.wms.service.impl;

import com.nanotech.wms.exception.CustomNotFoundException;
import com.nanotech.wms.mapper.ProductMapper;
import com.nanotech.wms.model.dto.request.ProductCreateDto;
import com.nanotech.wms.model.dto.response.ProductResponseDto;
import com.nanotech.wms.model.dto.response.ProductStockAmountDto;
import com.nanotech.wms.model.entity.Product;
import com.nanotech.wms.model.entity.User;
import com.nanotech.wms.repository.ProductRepository;
import com.nanotech.wms.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public void create(ProductCreateDto dto, User user) {
        Product product = productMapper.toEntity(dto);
        product.setCreatedBy(user);
        productRepository.save(product);
    }

    @Override
    public ProductResponseDto findById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Product not found with id %s".formatted(id)));
        return productMapper.toResponse(product);
    }

    @Override
    public List<ProductResponseDto> findAll() {
        List<Product> products = productRepository.findAll();
        return productMapper.toResponses(products);
    }

    @Override
    public List<ProductResponseDto> findAllByWarehouseId(UUID warehouseId) {
        List<Product> products = productRepository.findAllByWarehouseId(warehouseId);
        return productMapper.toResponses(products);
    }

    @Override
    public List<ProductStockAmountDto> findAllBetweenDatesInWarehouse(UUID warehouseId, Timestamp fromDate, Timestamp toDate) {
        return productRepository.findProductStockByWarehouseAndDate(warehouseId, fromDate, toDate);
    }

    @Override
    public List<ProductStockAmountDto> findAllBetweenDatesInOrganization(UUID orgId, Timestamp startDate, Timestamp endDate) {
        return productRepository.findProductStockByOrganizationAndDate(orgId, startDate, endDate);
    }

    @Override
    public void update(UUID id, ProductCreateDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Product not found with id %s".formatted(id)));
        Product newProduct = productMapper.toUpdatedEntity(product, dto);
        productRepository.save(newProduct);
    }

    @Override
    public void deleteById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Product not found with id %s".formatted(id)));
        product.setDeleted(true);
        productRepository.save(product);
    }
}
