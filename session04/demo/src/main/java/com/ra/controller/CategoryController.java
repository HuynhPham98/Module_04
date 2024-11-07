package com.ra.controller;

import com.ra.model.dto.category.CategoryDTO;
import com.ra.model.entity.Category;
import com.ra.repository.CategoryRepository;
import com.ra.service.category.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<Page<Category>> findAll(@PageableDefault(page = 0,size = 3,sort = "id",direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Category> categories = categoryService.findAll(pageable);
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<Category> save(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.create(categoryDTO);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@Valid @PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.findById(id);
        category.setId(id);
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setDescription(categoryDTO.getDescription());
        category.setStatus(true);
        Category categoryUpdate = categoryRepository.save(category);
        return ResponseEntity.ok(categoryUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Category>> searchCategory(@RequestParam String keyword,
                                                         @PageableDefault(page = 0,size = 3,sort = "id",direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Category> categories;
        if(keyword == null || keyword.trim().isEmpty()) {
            categories = categoryRepository.findAll(pageable);
        } else {
            categories = categoryRepository.findByCategoryNameContains(keyword, pageable);
        }
        return ResponseEntity.ok(categories);
    }
}
