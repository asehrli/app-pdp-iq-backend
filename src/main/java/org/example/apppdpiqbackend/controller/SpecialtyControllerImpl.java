package org.example.apppdpiqbackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.apppdpiqbackend.payload.AddSpecialtyDto;
import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.payload.SpecialtyDto;
import org.example.apppdpiqbackend.service.specialty.SpecialtyService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SpecialtyControllerImpl implements SpecialtyController {
    private final SpecialtyService specialtyService;

    @Override
    public HttpEntity<ApiResponse<List<SpecialtyDto>>> all() {
        return ResponseEntity.status(HttpStatus.OK).
                body(specialtyService.all());
    }

    @SneakyThrows
    @Override
    public HttpEntity<ApiResponse<SpecialtyDto>> add(AddSpecialtyDto specialtyDto,
                                                     MultipartFile multipartFile) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(specialtyService.add(specialtyDto, multipartFile));
    }

    @SneakyThrows
    @Override
    public HttpEntity<ApiResponse<SpecialtyDto>> edit(UUID id,
                                                      AddSpecialtyDto specialtyDto,
                                                      MultipartFile multipartFile) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(specialtyService.edit(id, specialtyDto, multipartFile));
    }


    @Override
    public HttpEntity<ApiResponse<SpecialtyDto>> one(UUID id) {
        return ResponseEntity.status(HttpStatus.OK).
                body(specialtyService.one(id));
    }

    @Override
    public HttpEntity<ApiResponse<?>> delete(UUID id) {
        return ResponseEntity.status(HttpStatus.OK).
                body(specialtyService.delete(id));
    }
}
