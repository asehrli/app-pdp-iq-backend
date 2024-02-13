package org.example.apppdpiqbackend.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EditLessonDTO(@NotBlank(message = "Name is required") String name,
                            @NotNull(message = "Modul is required") UUID id) {
}
