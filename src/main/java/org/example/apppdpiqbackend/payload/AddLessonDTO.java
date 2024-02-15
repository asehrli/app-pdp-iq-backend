package org.example.apppdpiqbackend.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddLessonDTO(@NotBlank(message = "Name is required") String name,
                           @NotNull(message = "Module is required") UUID moduleId) {
}
