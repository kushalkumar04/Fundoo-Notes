package com.example.fundoonotes.service.impl;

/*
 * User Service Implementation
 * Contains business logic for user registration
 */

import com.fundoonotes.dto.request.LoginRequestDto;
import com.fundoonotes.dto.response.LoginResponseDto;
import com.fundoonotes.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import com.fundoonotes.dto.request.UserRegisterRequestDto;
import com.fundoonotes.dto.response.UserResponseDto;
import com.fundoonotes.entity.User;
import com.fundoonotes.repository.UserRepository;
import com.fundoonotes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenUtil tokenUtil;

    @Override
    public LoginResponseDto login(LoginRequestDto dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = tokenUtil.generateToken(user.getId());

        return new LoginResponseDto(token, "Login successful");
    }

    @Override
    public UserResponseDto register(UserRegisterRequestDto dto) {

        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        User savedUser = userRepository.save(user);

        UserResponseDto response = new UserResponseDto();
        response.setFirstName(savedUser.getFirstName());
        response.setEmail(savedUser.getEmail());

        return response;
    }
}