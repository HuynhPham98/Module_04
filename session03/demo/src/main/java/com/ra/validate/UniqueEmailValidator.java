package com.ra.validate;

import com.ra.repository.CustormerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmailValidator implements ConstraintValidator<ValidateEmail, String> {

    @Autowired
    private CustormerRepository custormerRepository; // Repository để kiểm tra sự tồn tại

    @Override
    public void initialize(ValidateEmail constraintAnnotation) {
        // Khởi tạo nếu cần (không bắt buộc)
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isBlank()) {
            return true; // Trả về true nếu email trống, xử lý ràng buộc khác qua @NotBlank
        }
        // Kiểm tra email có tồn tại trong cơ sở dữ liệu không
        return !custormerRepository.existsByEmail(email);
    }
}

