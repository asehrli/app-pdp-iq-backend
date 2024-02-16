package org.example.apppdpiqbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.payload.ModuleDTO;
import org.example.apppdpiqbackend.service.ModelService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ModelControllerImpl implements ModuleController {

    private final ModelService modelService;

    @Override
    public HttpEntity<ApiResponse<ModuleDTO>> add(AddModuleDTO addModelDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(modelService.add(addModelDTO));
    }

    @Override
    public HttpEntity<ApiResponse<ModuleDTO>> one(UUID id) {
        return ResponseEntity.ok(modelService.one(id));
    }

    @Override
    public HttpEntity<ApiResponse<List<ModuleDTO>>> all() {
        return ResponseEntity.ok(modelService.list());
    }

    @Override
    public HttpEntity<ApiResponse<ModuleDTO>> edit(UUID id, EditModuleDTO editModelDTO) {
        return ResponseEntity.accepted().body(modelService.edit(id, editModelDTO));
    }

    @Override
    public HttpEntity<ApiResponse<?>> delete(UUID id) {
        modelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
