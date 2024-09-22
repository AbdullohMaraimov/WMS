package com.nanotech.wms.repository;

import com.nanotech.wms.model.entity.OutgoProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OutgoProductRepository extends JpaRepository<OutgoProduct, UUID> {
}
