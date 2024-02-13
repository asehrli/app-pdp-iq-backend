package org.example.apppdpiqbackend.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.apppdpiqbackend.enums.PermissionEnum;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto {
    UUID id;
    String name;
    List<PermissionEnum> permissions;
}