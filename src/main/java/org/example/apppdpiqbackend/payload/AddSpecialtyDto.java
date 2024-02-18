package org.example.apppdpiqbackend.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddSpecialtyDto {
    @NotBlank
    String name;
    @NotBlank
    String description;
}
