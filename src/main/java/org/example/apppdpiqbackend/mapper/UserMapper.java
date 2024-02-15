package org.example.apppdpiqbackend.mapper;

import org.example.apppdpiqbackend.entity.User;
import org.example.apppdpiqbackend.payload.RegisterDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(RegisterDto registerDto);

}
