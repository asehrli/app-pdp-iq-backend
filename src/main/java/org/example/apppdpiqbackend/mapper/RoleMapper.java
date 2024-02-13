package org.example.apppdpiqbackend.mapper;

import org.example.apppdpiqbackend.entity.Role;
import org.example.apppdpiqbackend.payload.AddRoleDto;
import org.example.apppdpiqbackend.payload.RoleDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toDto(Role role);
    Role toRole(AddRoleDto addRoleDto);
    List<RoleDto> toDtoList(List<Role> all);



}
