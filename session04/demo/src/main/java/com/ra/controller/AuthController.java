package com.ra.controller;

import com.ra.model.dto.login.UserLoginRequestDTO;
import com.ra.model.dto.login.UserLoginResponseDTO;
import com.ra.model.dto.register.UserRegisterRequestDTO;
import com.ra.service.user.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegisterRequestDTO userRegisterRequestDTO){
        authService.register(userRegisterRequestDTO);
        return ResponseEntity.ok("dang ky thanh cong");
    }
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {
        return ResponseEntity.ok(authService.login(userLoginRequestDTO));
    }

}
