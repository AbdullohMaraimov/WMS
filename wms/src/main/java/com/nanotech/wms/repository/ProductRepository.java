package com.nanotech.wms.repository;

import com.nanotech.wms.model.dto.response.ProductStockAmountDto;
import com.nanotech.wms.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("from Product p where p.warehouse.id = :id and p.amount > 0")
    List<Product> findAllByWarehouseId(@Param("id") UUID id);

    @Query("SELECT new com.nanotech.wms.model.dto.response.ProductStockAmountDto(p.name, " +
            "CAST(COALESCE(SUM(ip.amount), 0) - COALESCE(SUM(op.amount), 0) AS long)) " +
            "FROM Product p " +
            "LEFT JOIN IncomeProduct ip ON ip.product.id = p.id " +
            "LEFT JOIN OutgoProduct op ON op.product.id = p.id " +
            "LEFT JOIN Invoice i ON (ip.invoice.id = i.id OR op.invoice.id = i.id) " +
            "WHERE i.warehouse.id = :warehouseId " +
            "AND i.createdAt BETWEEN :startDate AND :endDate " +
            "GROUP BY p.id")
    List<ProductStockAmountDto> findProductStockByWarehouseAndDate(
            @Param("warehouseId") UUID warehouseId,
            @Param("startDate") Timestamp startDate,
            @Param("endDate") Timestamp endDate);


    @Query("SELECT new com.nanotech.wms.model.dto.response.ProductStockAmountDto(p.name, " +
            "cast(COALESCE(SUM(ip.amount), 0) - COALESCE(SUM(op.amount), 0) as long )) " +
            "FROM Product p " +
            "LEFT JOIN IncomeProduct ip ON ip.product.id = p.id " +
            "LEFT JOIN OutgoProduct op ON op.product.id = p.id " +
            "LEFT JOIN Invoice i ON (ip.invoice.id = i.id OR op.invoice.id = i.id) " +
            "WHERE i.organization.id = :orgId " +
            "AND i.createdAt BETWEEN :startDate AND :endDate " +
            "GROUP BY p.id")
    List<ProductStockAmountDto> findProductStockByOrganizationAndDate(
            @Param("orgId") UUID orgId,
            @Param("startDate") Timestamp startDate,
            @Param("endDate") Timestamp endDate);

}
