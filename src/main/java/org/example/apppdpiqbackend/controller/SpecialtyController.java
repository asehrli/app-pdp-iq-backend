package org.example.apppdpiqbackend.controller;

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
    @PostMapping(value = "/add")
         @PreAuthorize(value = "hasRole('ADD_SPECIALTY')")
    HttpEntity<ApiResponse<SpecialtyDto>> add(
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("name") String name,
            @RequestParam("description") String description);

    String BASE_PATH = "/api/specialty";

    @GetMapping()
          @PreAuthorize(value = "hasRole('GET_SPECIALTY')")
    HttpEntity<ApiResponse<List<SpecialtyDto>>> all();

    @PutMapping("/{id}")
          @PreAuthorize(value = "hasRole('EDIT_SPECIALTY')")
    HttpEntity<ApiResponse<SpecialtyDto>> edit(@PathVariable UUID id,
                                               @RequestParam("name") String name,
                                               @RequestParam("description") String description,
                                               @RequestParam("photo") MultipartFile multipartFile);

    @GetMapping("/{id}")
          @PreAuthorize(value = "hasRole('GET_SPECIALTY')")
    HttpEntity<ApiResponse<SpecialtyDto>> one(@PathVariable UUID id);

    @DeleteMapping("/{id}")
          @PreAuthorize(value = "hasRole('DELETE_SPECIALTY')")
    HttpEntity<ApiResponse<?>> delete(@PathVariable UUID id);
}
