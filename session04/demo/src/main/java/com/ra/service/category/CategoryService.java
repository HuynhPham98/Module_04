package com.ra.service.category;

import com.ra.model.dto.category.CategoryDTO;
import com.ra.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<Category> findAll(Pageable pageable);
    Category create(CategoryDTO categoryDTO);
    Category findById(Long id);
    void delete(Long id);
    Page<Category> findByCategoryName(String keyword, Pageable pageable);
}
