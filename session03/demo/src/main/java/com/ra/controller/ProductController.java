package com.ra.controller;

import com.ra.model.dto.product.ProductDTO;
import com.ra.model.entity.category.Category;
import com.ra.model.entity.product.Product;
import com.ra.service.category.CategoryService;
import com.ra.service.product.ProductService;
import com.ra.service.uploadFile.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UploadFileService uploadFileService;

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(@PageableDefault(page = 0,size = 3,sort = "productId",direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Product> products = productService.findAll(pageable);
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Product> save(@ModelAttribute ProductDTO productDTO) {
        Product newProduct = productService.create(productDTO);
        return ResponseEntity.ok(newProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @ModelAttribute ProductDTO productDTO) {
        Product product = productService.findById(id);
        if (product != null) {
            product.setProductId(id);
            product.setProductName(productDTO.getProductName());
            product.setPrice(productDTO.getPrice());
            product.setStock(productDTO.getStock());

            Category category = categoryService.findById(productDTO.getCategoryId());
            product.setCategory(category);
            product.setStatus(true);

            MultipartFile image = productDTO.getImage();
            if (image != null) {
                product.setImage(uploadFileService.uploadFile(image));
            }
        } else {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Product>> searchProducts(@RequestParam String keyword, @PageableDefault(page = 0,size = 3,sort = "productId",direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Product> products;
        if(keyword == null || keyword.isEmpty()) {
            products = productService.findAll(pageable);
        } else {
            products = productService.searchProduct(keyword, pageable);
        }
        return ResponseEntity.ok(products);
    }
}
