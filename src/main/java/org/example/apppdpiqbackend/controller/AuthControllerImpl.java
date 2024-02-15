package org.example.apppdpiqbackend.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.payload.LoginDto;
import org.example.apppdpiqbackend.payload.RegisterDto;
import org.example.apppdpiqbackend.payload.TokenDto;
import org.example.apppdpiqbackend.service.AuthService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    final AuthService authService;
    @Override
    public HttpEntity<ApiResponse<TokenDto>> register(RegisterDto registerDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerDto));
    }

    @Override
    public HttpEntity<ApiResponse<TokenDto>> login(LoginDto loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }
}
