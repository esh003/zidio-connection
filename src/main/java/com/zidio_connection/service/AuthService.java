package com.zidio_connection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zidio_connection.dto.AuthResponseDTO;
import com.zidio_connection.dto.LoginRequestDTO;
import com.zidio_connection.dto.UserDTO;
import com.zidio_connection.entity.Users;
import com.zidio_connection.repository.UserRepository;
import com.zidio_connection.security.JWTToken;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTToken jwtToken;

    public Users register(UserDTO dto) {
        Users user = new Users();
        user.setUserName(dto.getUserName());
        user.setUserEmail(dto.getUserEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        return userRepo.save(user);
    }

    public AuthResponseDTO login(LoginRequestDTO dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUserEmail(), dto.getPassword()));

        String token = jwtToken.generateToken(dto.getUserEmail(), "USER");
        return new AuthResponseDTO(token, "Token generated successfully");
    }
}
