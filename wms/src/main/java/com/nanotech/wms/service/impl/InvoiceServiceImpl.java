package com.nanotech.wms.service.impl;

import com.nanotech.wms.exception.CustomInsufficientProductException;
import com.nanotech.wms.exception.CustomNotFoundException;
import com.nanotech.wms.mapper.IncomeProductMapper;
import com.nanotech.wms.mapper.InvoiceMapper;
import com.nanotech.wms.mapper.OutgoProductMapper;
import com.nanotech.wms.model.dto.request.IncomeProductCreateDto;
import com.nanotech.wms.model.dto.request.InvoiceCreateDto;
import com.nanotech.wms.model.dto.request.OutgoProductCreateDto;
import com.nanotech.wms.model.dto.response.InvoiceResponseDto;
import com.nanotech.wms.model.entity.*;
import com.nanotech.wms.repository.InvoiceRepository;
import com.nanotech.wms.repository.ProductRepository;
import com.nanotech.wms.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final IncomeProductMapper incomeProductMapper;
    private final OutgoProductMapper outgoProductMapper;
    private final ProductRepository productRepository;

    @Override
    public void create(InvoiceCreateDto dto, User user) {
        Invoice invoice = invoiceMapper.toEntity(dto);
        invoice.setCreatedBy(user);

        invoiceRepository.save(invoice);

        List<IncomeProductCreateDto> incomeProductCreateDtos = dto.incomeProductsList();
        List<OutgoProductCreateDto> outgoProductCreateDtos = dto.outgoProductList();

        if (incomeProductCreateDtos != null && !incomeProductCreateDtos.isEmpty()) {
            List<IncomeProduct> incomeProducts = incomeProductMapper.toEntities(incomeProductCreateDtos);
            for (IncomeProduct incomeProduct : incomeProducts) {
                incomeProduct.setInvoice(invoice);

                Product product = incomeProduct.getProduct();

                Long amountToAdd = incomeProduct.getAmount();
                Long existingAmount = product.getAmount();
                product.setAmount(existingAmount + amountToAdd);

                product.setUpdatedBy(user);
                productRepository.save(product);
            }
            invoice.setIncomeProducts(incomeProducts);
        }

        if (outgoProductCreateDtos != null && !outgoProductCreateDtos.isEmpty()) {
            List<OutgoProduct> outgoProducts = outgoProductMapper.toEntities(outgoProductCreateDtos);
            for (OutgoProduct outgoProduct : outgoProducts) {
                outgoProduct.setInvoice(invoice);

                Product product = outgoProduct.getProduct();
                Long existingAmount = product.getAmount();
                Long amountToSubtract = outgoProduct.getAmount();

                if (existingAmount < amountToSubtract) {
                    throw new CustomInsufficientProductException("Product with id %s is less then required".formatted(outgoProduct.getProduct().getId()));
                }

                product.setAmount(existingAmount - amountToSubtract);
                product.setUpdatedBy(user);
                productRepository.save(product);

            }
            invoice.setOutgoProducts(outgoProducts);
        }

        invoiceRepository.save(invoice);
    }

    @Override
    public InvoiceResponseDto findById(UUID id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Invoice with id %s not found".formatted(id)));
        return invoiceMapper.toResponse(invoice);
    }

    @Override
    public List<InvoiceResponseDto> findAll() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoiceMapper.toResponses(invoices);
    }

    @Override
    public void deleteById(UUID id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Invoice with id %s not found".formatted(id)));
        invoice.setDeleted(true);
        invoiceRepository.save(invoice);
    }
}
