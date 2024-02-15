package org.example.apppdpiqbackend.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.apppdpiqbackend.entity.Module;
import org.example.apppdpiqbackend.exception.MyConflictException;
import org.example.apppdpiqbackend.exception.MyNotFoundException;
import org.example.apppdpiqbackend.mapper.ModuleMapper;
import org.example.apppdpiqbackend.payload.AddModuleDTO;
import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.payload.EditModuleDTO;
import org.example.apppdpiqbackend.payload.ModuleDTO;
import org.example.apppdpiqbackend.repository.ModuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ModelServiceImpl implements ModelService {

    final ModuleRepository moduleRepository;

    final ModuleMapper moduleMapper;


    @Override
    public ApiResponse<ModuleDTO> add(AddModuleDTO addModelDTO) {
        // todo existByNameAndSpecialityId
        if (moduleRepository.existsByName(addModelDTO.name())) {
            throw new MyConflictException("Module already exists! ");
        }
        return ApiResponse.success(moduleMapper.toDto(moduleRepository.save(moduleMapper.toModel(addModelDTO))));
    }

    @Override
    public ApiResponse<ModuleDTO> one(UUID id) {
        return ApiResponse.success(moduleMapper.toDto(moduleRepository.findById(id).orElseThrow(
                () -> new MyNotFoundException("Module not found by id :"))));
    }

    @Override
    public ApiResponse<List<ModuleDTO>> list() {
        return ApiResponse.success(moduleMapper.toDtoList(moduleRepository.findAll()));
    }

    @Override
    public ApiResponse<ModuleDTO> edit(UUID id, EditModuleDTO editModelDTO) {
        Module editModule = moduleRepository.findById(id).orElseThrow(() -> new MyNotFoundException("Module not found by id"));

        // todo existByNameAndSpecialityIdAndIdNot
        if (moduleRepository.existsByNameAndIdNot(editModule.getName(), id))
            throw new MyConflictException("Module already exists!");

        editModule.setName(editModule.getName());
        // todo Speciality is not edited
        editModule.setSpecialty(editModule.getSpecialty());

        return ApiResponse.success(moduleMapper.toDto(moduleRepository.save(editModule)));
    }

    @Override
    public void delete(UUID id) {
        if (!moduleRepository.existsById(id))
            throw new MyNotFoundException("Module not found by id!");

        moduleRepository.deleteById(id);
    }
}
