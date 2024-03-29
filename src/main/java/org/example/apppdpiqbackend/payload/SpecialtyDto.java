package org.example.apppdpiqbackend.payload;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpecialtyDto {
    UUID id;
    String name;
    AttachmentDto attachment;
    String description;
}
