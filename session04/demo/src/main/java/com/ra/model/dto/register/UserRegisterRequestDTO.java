package com.ra.model.dto.register;

import com.ra.validate.UniqueUserName;
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
public class UserRegisterRequestDTO {
    @NotBlank(message = "Tài khoản không được để trống")
    @UniqueUserName(message = "Tài khoản đã tồn tại!")
    private String username;
    @Length(min = 4,message = "Mật khẩu từ 4 ký tự trở lên")
    private String password;
}
