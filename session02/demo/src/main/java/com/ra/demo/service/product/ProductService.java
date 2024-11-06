package com.ra.demo.service.product;

import com.ra.demo.dto.ProductDTO;
import com.ra.demo.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product create(ProductDTO productDTO);
    Product findById(Long id);
    void delete(Long id);
    Page<Product> findAll(Pageable pageable);
    Page<Product> searchProducts(String keyword, Pageable pageable);
}
