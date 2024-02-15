package org.example.apppdpiqbackend.controller;

import jakarta.validation.Valid;
import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.payload.LoginDto;
import org.example.apppdpiqbackend.payload.RegisterDto;
import org.example.apppdpiqbackend.payload.TokenDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(AuthController.BP)
public interface AuthController {
    String BP = "/api/auth";
    @PostMapping("/register")
    HttpEntity<ApiResponse<TokenDto>> register(@Valid @RequestBody RegisterDto registerDto);
    @PostMapping("/login")
    HttpEntity<ApiResponse<TokenDto>> login(@Valid @RequestBody LoginDto loginDto);

    @GetMapping
    default HttpEntity<ApiResponse<String>> test() {
        return ResponseEntity.ok(ApiResponse.success("Open way"));
    }
}
