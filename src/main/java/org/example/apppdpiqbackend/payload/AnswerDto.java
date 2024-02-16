package org.example.apppdpiqbackend.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AnswerDto {
    private UUID answerId;
    @NotBlank
    private String text;
    @NotNull
    private UUID questionId;
    private boolean correct;
}
