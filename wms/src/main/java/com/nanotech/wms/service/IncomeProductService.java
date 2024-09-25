package com.nanotech.wms.service;

import com.nanotech.wms.model.dto.request.IncomeProductCreateDto;
import com.nanotech.wms.model.dto.response.IncomeProductResponseDto;

import java.util.List;
import java.util.UUID;

public interface IncomeProductService {

    void create(IncomeProductCreateDto dto);

    IncomeProductResponseDto findById(UUID id);

    List<IncomeProductResponseDto> findAll();

    void update(UUID uuid, IncomeProductCreateDto dto);

    void deleteById(UUID id);

}
