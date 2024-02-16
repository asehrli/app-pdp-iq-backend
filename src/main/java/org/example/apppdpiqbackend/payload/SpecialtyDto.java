package org.example.apppdpiqbackend.payload;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.apppdpiqbackend.entity.Attachment;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpecialtyDto {
    UUID id;
    String name;
    Attachment photo;
    String description;
}
