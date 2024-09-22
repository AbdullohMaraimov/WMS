package com.nanotech.wms.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cs_orgs")
public class Organization extends BaseEntity{
    private String name;
    private String tin;
}
