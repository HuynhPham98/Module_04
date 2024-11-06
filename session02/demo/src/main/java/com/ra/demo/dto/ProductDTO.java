package com.ra.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {
    @NotBlank(message = "Tên sản phẩm không được để trống.")
    private String productName;
    @Min(value = 1,message = "Giá sản phẩm lớn hơn 0.")
    private double price;
    private MultipartFile image;
    @Min(value = 1,message = "Category ID không hợp lệ.")
    private Long categoryId;
}
