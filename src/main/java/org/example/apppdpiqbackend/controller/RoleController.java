package org.example.apppdpiqbackend.controller;

import org.example.apppdpiqbackend.payload.AddRoleDto;
import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.payload.EditRoleDto;
import org.example.apppdpiqbackend.payload.RoleDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(RoleController.BASE_PATH)
public interface RoleController {
    String BASE_PATH = "/api/role";

    @GetMapping("/test")
    default HttpEntity<ApiResponse<String>> test() {
        return ResponseEntity.ok(ApiResponse.success("Close way test!!!"));
    }

    @PostMapping
    HttpEntity<ApiResponse<RoleDto>> add(@RequestBody AddRoleDto addRoleDto);

    @GetMapping("/{id}")
    HttpEntity<ApiResponse<RoleDto>> one(@PathVariable UUID id);

    @GetMapping
    HttpEntity<ApiResponse<List<RoleDto>>> all();

    @PutMapping("/{id}")
    HttpEntity<ApiResponse<RoleDto>> edit(@PathVariable UUID id,
                                          @RequestBody EditRoleDto editRoleDto);

    @DeleteMapping("/{id}")
    HttpEntity<ApiResponse<?>> delete(@PathVariable UUID id);

}
