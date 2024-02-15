package org.example.apppdpiqbackend.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.apppdpiqbackend.entity.Role;
import org.example.apppdpiqbackend.entity.User;
import org.example.apppdpiqbackend.exception.MyConflictException;
import org.example.apppdpiqbackend.exception.MyNotFoundException;
import org.example.apppdpiqbackend.mapper.UserMapper;
import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.payload.LoginDto;
import org.example.apppdpiqbackend.payload.RegisterDto;
import org.example.apppdpiqbackend.payload.TokenDto;
import org.example.apppdpiqbackend.repository.RoleRepository;
import org.example.apppdpiqbackend.repository.UserRepository;
import org.example.apppdpiqbackend.security.JwtProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {
    final UserRepository userRepository;
    final UserMapper userMapper;
    final JwtProvider jwtProvider;
    final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public ApiResponse<TokenDto> register(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.email()))
            throw new MyConflictException("Email already taken! Please, login");

        Role role = roleRepository.findByName("USER").orElseThrow();

        User entity = userMapper.toEntity(registerDto);
        entity.setRoles(List.of(role));
        userRepository.save(entity);

        return ApiResponse.success(new TokenDto(jwtProvider.generateToken(registerDto.email())));
    }

    @Override
    public ApiResponse<TokenDto> login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.email()).orElseThrow(() -> new MyNotFoundException("User not found by email"));
        if (!passwordEncoder.matches(loginDto.password(), user.getPassword()))
            throw new MyNotFoundException("User password is wrong!");

        return ApiResponse.success(new TokenDto(jwtProvider.generateToken(loginDto.email())));
    }
}
