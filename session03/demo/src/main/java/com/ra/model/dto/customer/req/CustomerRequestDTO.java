package com.ra.model.dto.customer.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ra.validate.ValidateEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerRequestDTO {
    @NotBlank(message = "Email không đuợc để trống")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Email không hợp lệ")
    @ValidateEmail(message = "Email đã tồn tại")
    private String email;

    @NotBlank(message = "Họ tên không được để trống.")
    private String fullName;

    @Length(min = 6,message = "Mật khẩu từ 6 ký tự trở lên.")
    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    @Past
    private LocalDate birthday;
}
