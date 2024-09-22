package com.nanotech.wms.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cs_income_products")
public class IncomeProduct extends BaseEntity{

    private Integer num;
    private Long amount;
    private Double price;
    private Double total;

    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
