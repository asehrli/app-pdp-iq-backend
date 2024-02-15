package org.example.apppdpiqbackend.payload;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.apppdpiqbackend.enums.PermissionEnum;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDto {
    UUID id;
    String name;
    List<PermissionEnum> permissions;
}