package com.nanotech.wms.mapper;

import com.nanotech.wms.exception.CustomNotFoundException;
import com.nanotech.wms.model.dto.request.InvoiceCreateDto;
import com.nanotech.wms.model.dto.response.InvoiceResponseDto;
import com.nanotech.wms.model.entity.Invoice;
import com.nanotech.wms.model.entity.Organization;
import com.nanotech.wms.model.entity.User;
import com.nanotech.wms.model.entity.Warehouse;
import com.nanotech.wms.repository.OrganizationRepository;
import com.nanotech.wms.repository.UserRepository;
import com.nanotech.wms.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InvoiceMapper {


    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    private final WarehouseRepository warehouseRepository;

    public Invoice toEntity(InvoiceCreateDto dto) {
        Invoice invoice = new Invoice();
        invoice.setStatus(dto.status());

        Organization organization = organizationRepository.findById(dto.organizationId())
                .orElseThrow(() -> new CustomNotFoundException("Organization with id %s not found".formatted(dto.organizationId())));

        User user = userRepository.findById(dto.customerId())
                .orElseThrow(() -> new CustomNotFoundException("Customer with id %s not found".formatted(dto.customerId())));

        Warehouse warehouse = warehouseRepository.findById(dto.warehouseId())
                .orElseThrow(() -> new CustomNotFoundException("Warehouse with id %s not found".formatted(dto.warehouseId())));

        invoice.setCustomer(user);
        invoice.setWarehouse(warehouse);
        invoice.setOrganization(organization);

        return invoice;
    }

    public InvoiceResponseDto toResponse(Invoice invoice) {
        return new InvoiceResponseDto(
                invoice.getId(),
                invoice.getStatus(),
                invoice.getOrganization().getId(),
                invoice.getCustomer().getId(),
                invoice.getWarehouse().getId()
        );
    }

    public List<InvoiceResponseDto> toResponses(List<Invoice> invoices) {
        List<InvoiceResponseDto> responseDtoList = new ArrayList<>();
        for (Invoice invoice : invoices) {
            responseDtoList.add(toResponse(invoice));
        }
        return responseDtoList;
    }

    public Invoice toUpdatedEntity(Invoice invoice, InvoiceCreateDto dto) {
        invoice.setStatus(dto.status());

        Organization organization = organizationRepository.findById(dto.organizationId())
                .orElseThrow(() -> new CustomNotFoundException("Organization with id %s not found".formatted(dto.organizationId())));

        User user = userRepository.findById(dto.customerId())
                .orElseThrow(() -> new CustomNotFoundException("Customer with id %s not found".formatted(dto.customerId())));

        Warehouse warehouse = warehouseRepository.findById(dto.warehouseId())
                .orElseThrow(() -> new CustomNotFoundException("Warehouse with id %s not found".formatted(dto.warehouseId())));

        invoice.setCustomer(user);
        invoice.setWarehouse(warehouse);
        invoice.setOrganization(organization);

        return invoice;
    }
}







