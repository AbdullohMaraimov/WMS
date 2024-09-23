package com.nanotech.wms.service;

import com.nanotech.wms.model.dto.request.OutgoProductCreateDto;
import com.nanotech.wms.model.dto.response.OutgoProductResponseDto;

import java.util.List;
import java.util.UUID;

public interface OutgoProductService {

    void create(OutgoProductCreateDto dto);

    OutgoProductResponseDto findById(UUID id);

    List<OutgoProductResponseDto> findAll();

    void update(UUID uuid, OutgoProductCreateDto dto);

    void deleteById(UUID id);

}
