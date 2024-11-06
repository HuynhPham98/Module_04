package com.ra.demo.controller;

import com.ra.demo.dto.ProductDTO;
import com.ra.demo.model.entity.Category;
import com.ra.demo.model.entity.Product;
import com.ra.demo.service.category.CategoryService;
import com.ra.demo.service.product.ProductService;
import com.ra.demo.service.uploadFile.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "2") int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> products = productService.findAll(pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@ModelAttribute ProductDTO productDTO) {
        Product newProduct = productService.create(productDTO);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        Product product = productService.findById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @ModelAttribute ProductDTO productDTO) {
        Product product = productService.findById(id);
        if (product != null) {
            product.setProductName(productDTO.getProductName());
            product.setPrice(productDTO.getPrice());
            Category category = categoryService.findById(productDTO.getCategoryId());
            product.setCategory(category);
            product.setStatus(true);

            MultipartFile image = productDTO.getImage();
            if (image != null) {
                product.setImage(uploadFileService.uploadFile(image));
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Product>> searchProducts(@RequestParam String keyword,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "2") int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> products;
        if(keyword == null || keyword.trim().isEmpty()) {
            products = productService.findAll(pageable);
        } else {
            products = productService.searchProducts(keyword, pageable);
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
