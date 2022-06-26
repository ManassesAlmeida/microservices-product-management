package com.manassesalmeida.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manassesalmeida.payment.entity.ProductSale;

@Repository
public interface ProductSaleRepository extends JpaRepository<ProductSale, Long>{

}
