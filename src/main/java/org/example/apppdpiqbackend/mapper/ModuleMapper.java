package org.example.apppdpiqbackend.mapper;

import org.example.apppdpiqbackend.entity.Module;
import org.example.apppdpiqbackend.payload.AddModuleDTO;
import org.example.apppdpiqbackend.payload.ModuleDTO;
import org.mapstruct.Mapper;


import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ModuleMapper {
    ModuleDTO toDto(Module module);
    List<ModuleDTO> toDtoList(List<Module> module);

    Module toModel(AddModuleDTO addModuleDTO);


}
