package com.ra.service.product;

import com.ra.model.dto.product.ProductDTO;
import com.ra.model.entity.category.Category;
import com.ra.model.entity.product.Product;
import com.ra.repository.CategoryRepository;
import com.ra.repository.ProductRepository;
import com.ra.service.uploadFile.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UploadFileService uploadFileService;


    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product create(ProductDTO productDTO) {
        Product product = new Product();

        MultipartFile imageFile = productDTO.getImage();
        if (imageFile != null && !imageFile.isEmpty()) {
            String imageName = uploadFileService.uploadFile(imageFile);
            product.setImage(imageName);
        }
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setStatus(true);

        Long categoryId = productDTO.getCategoryId();
        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId).orElse(null);
            product.setCategory(category);
        }
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
    public Page<Product> searchProduct(String keyword, Pageable pageable) {
        return productRepository.findByProductName(keyword, pageable);
    }
}
