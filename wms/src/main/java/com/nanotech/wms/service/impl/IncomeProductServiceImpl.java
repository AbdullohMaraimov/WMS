package com.nanotech.wms.service.impl;

import com.nanotech.wms.exception.CustomNotFoundException;
import com.nanotech.wms.mapper.IncomeProductMapper;
import com.nanotech.wms.model.dto.request.IncomeProductCreateDto;
import com.nanotech.wms.model.dto.response.IncomeProductResponseDto;
import com.nanotech.wms.model.entity.IncomeProduct;
import com.nanotech.wms.repository.IncomeProductRepository;
import com.nanotech.wms.service.IncomeProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class IncomeProductServiceImpl implements IncomeProductService {

    private final IncomeProductRepository incomeProductRepository;
    private final IncomeProductMapper incomeProductMapper;

    @Override
    public void create(IncomeProductCreateDto dto) {
        IncomeProduct outgoProduct = incomeProductMapper.toEntity(dto);
        incomeProductRepository.save(outgoProduct);
    }

    @Override
    public IncomeProductResponseDto findById(UUID id) {
        IncomeProduct outgoProduct = incomeProductRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("IncomeProduct with id %s not found".formatted(id)));
        return incomeProductMapper.toResponse(outgoProduct);
    }

    @Override
    public List<IncomeProductResponseDto> findAll() {
        List<IncomeProduct> outgoProducts = incomeProductRepository.findAll();
        return incomeProductMapper.toResponses(outgoProducts);
    }

    @Override
    public void update(UUID uuid, IncomeProductCreateDto dto) {
        IncomeProduct product = incomeProductRepository.findById(uuid)
                .orElseThrow(() -> new CustomNotFoundException("IncomeProduct with id %s not found".formatted(uuid)));
        IncomeProduct updatedProduct = incomeProductMapper.toUpdatedEntity(product, dto);
        incomeProductRepository.save(updatedProduct);
    }

    @Override
    public void deleteById(UUID id) {
        IncomeProduct product = incomeProductRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("IncomeProduct with id %s not found".formatted(id)));
        product.setDeleted(true);
        incomeProductRepository.save(product);
    }
}
