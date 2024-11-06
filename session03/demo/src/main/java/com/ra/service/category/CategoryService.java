package com.ra.service.category;

import com.ra.model.dto.category.CategoryDTO;
import com.ra.model.entity.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    Page<Category> findAll(Pageable pageable);
    Category findById(Long id);
    Category create(CategoryDTO categoryDTO);
    void delete(Long id);
    Page<Category> searchCategories(String keyword, Pageable pageable);
}
