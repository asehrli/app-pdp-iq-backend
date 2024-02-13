package org.example.apppdpiqbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.apppdpiqbackend.payload.AddRoleDto;
import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.payload.EditRoleDto;
import org.example.apppdpiqbackend.payload.RoleDto;
import org.example.apppdpiqbackend.service.RoleService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RoleControllerImpl implements RoleController {
    private final RoleService roleService;
    @Override
    public HttpEntity<ApiResponse<RoleDto>> add(AddRoleDto addRoleDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.add(addRoleDto));
    }

    @Override
    public HttpEntity<ApiResponse<RoleDto>> one(UUID id) {
        return ResponseEntity.ok(roleService.one(id));
    }

    @Override
    public HttpEntity<ApiResponse<List<RoleDto>>> all() {
        return ResponseEntity.ok(roleService.all());
    }

    @Override
    public HttpEntity<ApiResponse<RoleDto>> edit(UUID id, EditRoleDto editRoleDto) {
        return ResponseEntity.accepted().body(roleService.edit(id, editRoleDto));
    }

    @Override
    public HttpEntity<ApiResponse<?>> delete(UUID id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
