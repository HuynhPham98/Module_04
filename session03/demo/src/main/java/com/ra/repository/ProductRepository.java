package com.ra.repository;

import com.ra.model.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByProductName(String keyword, Pageable pageable);
    boolean existsByProductName(String productName);
}
