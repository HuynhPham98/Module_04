package com.ra.service.category;

import com.ra.exception.ResourceNotFoundException;
import com.ra.model.dto.category.CategoryDTO;
import com.ra.model.entity.category.Category;
import com.ra.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category findById(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category == null) {
            throw new ResourceNotFoundException("Danh mục không tìm thấy với ID: " + id);
        }
        return category;
    }

    @Override
    public Category create(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setDescription(categoryDTO.getDescription());
        category.setStatus(true);
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Page<Category> searchCategories(String keyword, Pageable pageable) {
        return categoryRepository.findByCategoryNameContains(keyword, pageable);
    }
}
