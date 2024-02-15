package org.example.apppdpiqbackend.service;

import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.payload.LoginDto;
import org.example.apppdpiqbackend.payload.RegisterDto;
import org.example.apppdpiqbackend.payload.TokenDto;

public interface AuthService {
    ApiResponse<TokenDto> register(RegisterDto registerDto);
    ApiResponse<TokenDto> login(LoginDto loginDto);
}
