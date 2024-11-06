package com.ra.demo.repository;

import com.ra.demo.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByProductNameContains(String keyword, Pageable pageable); ;
}
