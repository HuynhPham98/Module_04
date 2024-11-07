package com.ra.model.dto.login;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserLoginRequestDTO {
    private String username;
    private String password;
}
