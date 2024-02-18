package org.example.apppdpiqbackend.mapper;

import org.example.apppdpiqbackend.entity.Specialty;
import org.example.apppdpiqbackend.payload.AttachmentDto;
import org.example.apppdpiqbackend.payload.SpecialtyDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface SpecialMapper {
    AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);


    @Mappings({
            @Mapping(target = "attachment", source = "attachmentDto"),
            @Mapping(target = "id", source = "specialty.id")
    })
    SpecialtyDto toDto(Specialty specialty, AttachmentDto attachmentDto);

}
