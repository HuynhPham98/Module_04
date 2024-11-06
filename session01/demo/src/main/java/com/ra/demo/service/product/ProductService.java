package com.ra.demo.service.product;

import com.ra.demo.model.Employee;
import com.ra.demo.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    boolean create(Product product);
    Product findById(Long id);
    boolean update(Product product,Long id);
    void delete(Long id);
}
