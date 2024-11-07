package com.ra.service.user;

import com.ra.exception.DataExistException;
import com.ra.model.dto.login.UserLoginRequestDTO;
import com.ra.model.dto.login.UserLoginResponseDTO;
import com.ra.model.dto.register.UserRegisterRequestDTO;
import com.ra.model.entity.Role;
import com.ra.model.entity.User;
import com.ra.repository.RoleRepository;
import com.ra.repository.UserRepository;
import com.ra.security.UserPrinciple;
import com.ra.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void register(UserRegisterRequestDTO userRegisterRequestDTO){

        // Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(userRegisterRequestDTO.getPassword());

        //Tạo đối tượng  mới
        User user = new User();
        user.setUsername(userRegisterRequestDTO.getUsername());
        user.setPassword(encodedPassword);
        Set<Role> roles = new HashSet<>();
        Role roleName = roleRepository.findByName("ROLE_USER");
        roles.add(roleName);
        user.setRoles(roles);
        user.setStatus(true);

        // Lưu người dùng vào cơ sở dữ liệu
        userRepository.save(user);
    }

    @Override
    public UserLoginResponseDTO login(UserLoginRequestDTO userLoginRequestDTO) {
        Authentication authentication;
        authentication = authenticationProvider
                .authenticate(new UsernamePasswordAuthenticationToken(userLoginRequestDTO.getUsername(), userLoginRequestDTO.getPassword()));
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        String token = jwtProvider.generateToken(userPrinciple);
        return UserLoginResponseDTO.builder()
                .accessToken(token)
                .typeToken("Bearer")
                .username(userPrinciple.getUsername())
                .roles(userPrinciple.getUser().getRoles())
                .build();
    }
}
