package org.example.apppdpiqbackend.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddSpecialtyDto {
    UUID uuid;
    @NotBlank
    String name;
    @NotBlank
    String description;
}
