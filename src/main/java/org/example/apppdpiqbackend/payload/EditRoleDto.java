package org.example.apppdpiqbackend.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.apppdpiqbackend.enums.PermissionEnum;

import java.util.List;

public record EditRoleDto(@NotBlank(message = "Name is required") String name,
                          @NotNull(message = "Permissions is required") List<PermissionEnum> permissions) {
}
