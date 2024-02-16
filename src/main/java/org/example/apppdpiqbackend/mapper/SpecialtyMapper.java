package org.example.apppdpiqbackend.mapper;

import org.example.apppdpiqbackend.entity.Specialty;
import org.example.apppdpiqbackend.payload.SpecialtyDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpecialtyMapper {
    SpecialtyDto toDto(Specialty specialty);
}
