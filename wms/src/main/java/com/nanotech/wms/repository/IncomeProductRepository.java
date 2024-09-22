package com.nanotech.wms.repository;

import com.nanotech.wms.model.entity.IncomeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IncomeProductRepository extends JpaRepository<IncomeProduct, UUID> {
}
