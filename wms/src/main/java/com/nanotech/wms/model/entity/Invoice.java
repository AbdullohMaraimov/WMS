package com.nanotech.wms.model.entity;

import com.nanotech.wms.model.constant.InvoiceStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cs_invoices")
public class Invoice extends BaseEntity{

    private Integer num;
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

}
