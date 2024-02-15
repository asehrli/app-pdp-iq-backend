package org.example.apppdpiqbackend.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDto(@NotBlank(message = "Email is required")
                       @Email(message = "Email is invalid")
                       String email,
                       @NotBlank(message = "Password is required")
                       String password) {
}
