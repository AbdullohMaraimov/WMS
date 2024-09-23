package com.nanotech.wms.service.impl;

import com.nanotech.wms.exception.CustomNotFoundException;
import com.nanotech.wms.mapper.OutgoProductMapper;
import com.nanotech.wms.model.dto.request.OutgoProductCreateDto;
import com.nanotech.wms.model.dto.response.OutgoProductResponseDto;
import com.nanotech.wms.model.entity.OutgoProduct;
import com.nanotech.wms.repository.OutgoProductRepository;
import com.nanotech.wms.service.OutgoProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OutgoProductServiceImpl implements OutgoProductService {

    private final OutgoProductRepository outgoProductRepository;
    private final OutgoProductMapper outgoProductMapper;

    @Override
    public void create(OutgoProductCreateDto dto) {
        OutgoProduct outgoProduct = outgoProductMapper.toEntity(dto);
        outgoProductRepository.save(outgoProduct);
    }

    @Override
    public OutgoProductResponseDto findById(UUID id) {
        OutgoProduct outgoProduct = outgoProductRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("OutgoProduct with id %s not found".formatted(id)));
        return outgoProductMapper.toResponse(outgoProduct);
    }

    @Override
    public List<OutgoProductResponseDto> findAll() {
        List<OutgoProduct> outgoProducts = outgoProductRepository.findAll();
        return outgoProductMapper.toResponses(outgoProducts);
    }

    @Override
    public void update(UUID uuid, OutgoProductCreateDto dto) {
        OutgoProduct product = outgoProductRepository.findById(uuid)
                .orElseThrow(() -> new CustomNotFoundException("OutgoProduct with id %s not found".formatted(uuid)));
        OutgoProduct updatedProduct = outgoProductMapper.toUpdatedEntity(product, dto);
        outgoProductRepository.save(updatedProduct);
    }

    @Override
    public void deleteById(UUID id) {
        OutgoProduct product = outgoProductRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("OutgoProduct with id %s not found".formatted(id)));
        product.setDeleted(true);
        outgoProductRepository.save(product);
    }
}
