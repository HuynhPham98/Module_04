package com.ra.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = UniqueCategoryValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateCategory {
    String message() default "Danh mục đã tồn tại.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
