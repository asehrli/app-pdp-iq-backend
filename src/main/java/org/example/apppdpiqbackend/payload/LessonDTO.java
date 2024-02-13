package org.example.apppdpiqbackend.payload;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonDTO {
    UUID id;
    String name;
    UUID moduleId;
}
