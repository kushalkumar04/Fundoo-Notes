package com.example.fundoonotes.service;
import com.fundoonotes.dto.request.LoginRequestDto;
import com.fundoonotes.dto.response.LoginResponseDto;

LoginResponseDto login(LoginRequestDto dto);


import com.fundoonotes.dto.request.LoginRequestDto;
import com.fundoonotes.dto.response.LoginResponseDto;

LoginResponseDto login(LoginRequestDto dto);
public interface UserService {

    UserResponseDto register(UserRegisterRequestDto dto);
}