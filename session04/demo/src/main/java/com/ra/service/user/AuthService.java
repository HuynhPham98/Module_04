package com.ra.service.user;

import com.ra.exception.DataExistException;
import com.ra.model.dto.login.UserLoginRequestDTO;
import com.ra.model.dto.login.UserLoginResponseDTO;
import com.ra.model.dto.register.UserRegisterRequestDTO;

public interface AuthService {
    void register(UserRegisterRequestDTO userRegisterRequestDTO);
    UserLoginResponseDTO login(UserLoginRequestDTO userLoginRequestDTO);
}
