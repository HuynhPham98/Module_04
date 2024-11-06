package com.ra.demo.service.product;

import com.ra.demo.model.Product;
import com.ra.demo.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public boolean create(Product product) {
        productRepository.save(product);
        return true;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public boolean update(Product product, Long id) {
        // Tìm sản phẩm hiện tại theo ID
        Product existingProduct = productRepository.findById(id).orElse(null);

        // Kiểm tra xem sản phẩm có tồn tại không
        if (existingProduct != null) {
            // Cập nhật các trường của existingProduct từ product truyền vào
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStock(product.getStock());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setStatus(product.getStatus());

            // Lưu sản phẩm đã cập nhật
            productRepository.save(existingProduct);
            return true; // Trả về true nếu cập nhật thành công
        }

        return false;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
