package org.example.apppdpiqbackend.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.apppdpiqbackend.entity.Role;
import org.example.apppdpiqbackend.exception.MyConflictException;
import org.example.apppdpiqbackend.exception.MyNotFoundException;
import org.example.apppdpiqbackend.mapper.RoleMapper;
import org.example.apppdpiqbackend.payload.AddRoleDto;
import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.payload.EditRoleDto;
import org.example.apppdpiqbackend.payload.RoleDto;
import org.example.apppdpiqbackend.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleServiceImpl implements RoleService {

    final RoleRepository roleRepository;

    final RoleMapper roleMapper;
    @Override
    public ApiResponse<RoleDto> add(AddRoleDto addRoleDto) {
        if (roleRepository.existsByName(addRoleDto.name()))
            throw new MyConflictException("Role already exists!");

        return ApiResponse.success(roleMapper.toDto(roleRepository.save(roleMapper.toRole(addRoleDto))));
    }

    @Override
    public ApiResponse<RoleDto> one(UUID id) {
        return ApiResponse.success(roleMapper.toDto(
                roleRepository.findById(id).orElseThrow(() ->
                        new MyNotFoundException("Role not found by id"))));
    }

    @Override
    public ApiResponse<List<RoleDto>> all() {
        return ApiResponse.success(roleMapper.toDtoList(roleRepository.findAll()));
    }

    @Override
    public ApiResponse<RoleDto> edit(UUID id, EditRoleDto editRoleDto) {
        Role editingRole = roleRepository.findById(id).orElseThrow(() -> new MyNotFoundException("Role not found by id"));

        if (roleRepository.existsByNameAndIdNot(editingRole.getName(), id))
            throw new MyConflictException("Role already exists!");

        editingRole.setName(editingRole.getName());
        editingRole.setPermissions(editingRole.getPermissions());

        return ApiResponse.success(roleMapper.toDto(roleRepository.save(editingRole)));
    }

    @Override
    public void delete(UUID id) {
        if (!roleRepository.existsById(id))
            throw new MyNotFoundException("Role not found by id!");

        roleRepository.deleteById(id);
    }
}
