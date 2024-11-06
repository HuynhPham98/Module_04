package com.ra.model.dto.product;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {
    @Length(min = 3, message = "Tên sản phẩm từ 3 ký tự trở lên.")
    private String productName;
    @Min(value = 1,message = "Giá sản phẩm lớn hơn 0.")
    private double price;
    @Min(value = 1,message = "Số lượng sản phẩm lớn hơn 0.")
    private int stock;
    private MultipartFile image;
    @Min(value = 1,message = "Category ID không hợp lệ.")
    private Long categoryId;
    private boolean status;
}
