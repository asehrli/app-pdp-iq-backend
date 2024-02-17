package org.example.apppdpiqbackend.controller;

import jakarta.validation.Valid;
import org.example.apppdpiqbackend.payload.AddSpecialtyDto;
import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.payload.SpecialtyDto;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RequestMapping(SpecialtyController.BASE_PATH)
public interface SpecialtyController {
    String BASE_PATH = "/api/specialty";

    @GetMapping("/")
    @PreAuthorize(value = "hasRole('GET_SPECIALTY')")
    HttpEntity<ApiResponse<List<SpecialtyDto>>> all();

    @PostMapping("/add")
    @PreAuthorize(value = "hasRole('ADD_SPECIALTY')")
    HttpEntity<ApiResponse<SpecialtyDto>> add(@RequestBody
                                              @Valid AddSpecialtyDto specialtyDto,
                                              @RequestParam("photo") MultipartFile multipartFile);

    @PutMapping("/{id}")
    @PreAuthorize(value = "hasRole('EDIT_SPECIALTY')")
    HttpEntity<ApiResponse<SpecialtyDto>> edit(@PathVariable UUID id,
                                               @RequestBody AddSpecialtyDto specialtyDto,
                                               @RequestParam("photo") MultipartFile multipartFile);

    @GetMapping("/{id}")
    @PreAuthorize(value = "hasRole('GET_SPECIALTY')")
    HttpEntity<ApiResponse<SpecialtyDto>> one(@PathVariable UUID id);

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasRole('DELETE_SPECIALTY')")
    HttpEntity<ApiResponse<?>> delete(@PathVariable UUID id);
}
