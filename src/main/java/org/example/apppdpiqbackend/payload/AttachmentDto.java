package org.example.apppdpiqbackend.payload;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttachmentDto {
    UUID id;

    String originalName;

    String contentType;

    long size;

    String contentUrl;
}
