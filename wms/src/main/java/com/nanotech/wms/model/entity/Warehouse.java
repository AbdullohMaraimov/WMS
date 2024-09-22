package com.nanotech.wms.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cs_warehouses")
public class Warehouse extends BaseEntity{

    private String name;

    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization organization;

}
