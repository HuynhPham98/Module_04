package com.ra.service.product;

import com.ra.model.dto.product.ProductDTO;
import com.ra.model.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);
    Product create(ProductDTO productDTO);
    Product findById(Long id);
    void delete(Long id);
    Page<Product> searchProduct(String keyword, Pageable pageable);
}
