package org.example.apppdpiqbackend.payload;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.apppdpiqbackend.entity.Specialty;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ModuleDTO {
    UUID id;
    String name;
    SpecialtyDto specialty;
}
