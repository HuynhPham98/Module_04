package com.ra.model.dto.category;

import com.ra.validate.ValidateCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO {
    @Length(min = 3, message = "Tên danh mục từ 3 ký tự trở lên.")
    @ValidateCategory(message = "Danh mục đã tồn tại")
    private String categoryName;
    @NotBlank(message = "Mô tả danh mục không được để trống")
    private String description;
    private boolean status;
}
