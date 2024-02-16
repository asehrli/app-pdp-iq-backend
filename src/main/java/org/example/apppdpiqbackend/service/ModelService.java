package org.example.apppdpiqbackend.service;

import org.example.apppdpiqbackend.payload.AddModuleDTO;
import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.payload.EditModuleDTO;
import org.example.apppdpiqbackend.payload.ModuleDTO;

import java.util.List;
import java.util.UUID;

public interface ModelService {
    ApiResponse<ModuleDTO> add(AddModuleDTO addModelDTO);

    ApiResponse<ModuleDTO> one(UUID id);

    ApiResponse<List<ModuleDTO>> list();

    ApiResponse<ModuleDTO> edit(UUID id, EditModuleDTO editModelDTO);

    void delete(UUID id);

}
