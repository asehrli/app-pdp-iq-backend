package org.example.apppdpiqbackend.service;

import org.example.apppdpiqbackend.payload.AddRoleDto;
import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.payload.EditRoleDto;
import org.example.apppdpiqbackend.payload.RoleDto;
import org.springframework.http.HttpEntity;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    ApiResponse<RoleDto> add(AddRoleDto addRoleDto);

    ApiResponse<RoleDto> one(UUID id);

    ApiResponse<List<RoleDto>> all();

    ApiResponse<RoleDto> edit(UUID id, EditRoleDto editRoleDto);

    void delete(UUID id);
}
