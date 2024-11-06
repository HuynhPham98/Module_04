package com.ra.demo.repository.product;

import com.ra.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByNameContains(String name);
}
