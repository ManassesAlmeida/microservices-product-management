package com.manassesalmeida.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manassesalmeida.crud.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
