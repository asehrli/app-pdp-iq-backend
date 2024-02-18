package org.example.apppdpiqbackend.mapper;

import org.example.apppdpiqbackend.entity.Attachment;
import org.example.apppdpiqbackend.payload.AttachmentDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface AttachmentMapper {
    AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);

    AttachmentDto toDto(Attachment attachment);
}
