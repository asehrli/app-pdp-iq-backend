package org.example.apppdpiqbackend.controller;

import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.payload.ModuleDTO;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(ModuleController.BAHT_PATH)
public interface ModuleController {
    String BAHT_PATH = "/api/model";

    @PostMapping
    HttpEntity<ApiResponse<ModuleDTO>> add(@RequestBody AddModuleDTO addModelDTO);

    @GetMapping("/{id}")
    HttpEntity<ApiResponse<ModuleDTO>> one(@RequestBody UUID id);

    @GetMapping
    HttpEntity<ApiResponse<List<ModuleDTO>>> all();

    @PutMapping("/{id}")
    HttpEntity<ApiResponse<ModuleDTO>> edit(@PathVariable UUID id, @RequestBody EditModuleDTO editModelDTO);

    @DeleteMapping("/{id}")
    HttpEntity<ApiResponse<?>> delete(@PathVariable UUID id);


}
