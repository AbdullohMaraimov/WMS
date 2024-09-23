package com.nanotech.wms.service;

import com.nanotech.wms.model.dto.request.InvoiceCreateDto;
import com.nanotech.wms.model.dto.response.InvoiceResponseDto;
import com.nanotech.wms.model.entity.User;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {

    void create(InvoiceCreateDto dto, User user);

    InvoiceResponseDto findById(UUID id);

    List<InvoiceResponseDto> findAll();

    void deleteById(UUID id);

}
