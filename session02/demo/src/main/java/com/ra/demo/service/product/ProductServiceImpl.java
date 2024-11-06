package com.ra.demo.service.product;

import com.ra.demo.dto.ProductDTO;
import com.ra.demo.model.entity.Category;
import com.ra.demo.model.entity.Product;
import com.ra.demo.repository.CategoryRepository;
import com.ra.demo.repository.ProductRepository;
import com.ra.demo.service.uploadFile.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UploadFileService uploadFileService;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product create(ProductDTO productDTO) {
        Product product = new Product();

        MultipartFile imageFile = productDTO.getImage();
        if (imageFile != null && !imageFile.isEmpty()) {
                String fileName = uploadFileService.uploadFile(imageFile);
                product.setImage(fileName);
        }

        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());

        Long categoryId = productDTO.getCategoryId();
        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId).orElse(null);
            product.setCategory(category);
        }
        product.setStatus(true);
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> searchProducts(String keyword, Pageable pageable) {
        return productRepository.findByProductNameContains(keyword, pageable);
    }
}
