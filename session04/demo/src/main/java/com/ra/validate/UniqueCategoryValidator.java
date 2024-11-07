package com.ra.validate;

import com.ra.repository.CategoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueCategoryValidator implements ConstraintValidator<UniqueCategory, String> {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public boolean isValid(String categoryName, ConstraintValidatorContext constraintValidatorContext) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            return true;
        }
        return !categoryRepository.existsByCategoryName(categoryName);
    }
}
