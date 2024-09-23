package com.nanotech.wms.model.entity;

import com.nanotech.wms.model.constant.InvoiceStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cs_invoices")
public class Invoice extends BaseEntity{

    private InvoiceStatus status;

    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<IncomeProduct> incomeProducts;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<OutgoProduct> outgoProducts;

}
