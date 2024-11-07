package com.ra.model.dto.login;

import com.ra.model.entity.Role;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserLoginResponseDTO {
    private String username;
    private String accessToken;
    private String typeToken;
    private Set<Role> roles;
}
